package services;

import com.google.gson.Gson;
import db.Task;
import lombok.SneakyThrows;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {

    private final Gson GSON = new Gson();
    private static final String PATH = "src/main/resources/taskList.json";
    private final EncryptService encryptService = new EncryptService();

    @SneakyThrows
    public List<Task> getTasks(){
        FileInputStream fileInputStream = new FileInputStream(PATH);
        Task[] tasks = GSON.fromJson(readFile(fileInputStream), Task[].class);
        return Arrays.stream(tasks).sorted(new TaskComparator()).collect(Collectors.toList());
    }

    private static class TaskComparator implements Comparator<Task>{

        @Override
        public int compare(Task task, Task t1) {
            return Long.compare(task.getCreatedOn().getTime(), t1.getCreatedOn().getTime());
        }
    }
    private String readFile(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    @SneakyThrows
    public void addTask(Task task){
        FileInputStream fileInputStream = new FileInputStream(PATH);
        task.setCreatedOn(new Date());
        task.setActive(true);
        Task[] tasks = GSON.fromJson(readFile(fileInputStream), Task[].class);
        List<Task> taskList = new ArrayList<>(List.of(tasks));
        taskList.add(task);
        writeFile(GSON.toJson(taskList), PATH);
    }

    @SneakyThrows
    private void writeFile(String json, String path){
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(json);
        }
    }

    private void deleteFile(String path){
        File file = new File(path);
        file.delete();
    }

    public List<Task> getCompletedTasks(){
        List<Task> taskList = getTasks();
        return taskList.stream()
                .filter(task -> (task.getCompletedOn() != null && !task.isActive()))
                .collect(Collectors.toList());
    }

    public void removeTask(String name){
        List<Task> tasks = getTasks();
        tasks = tasks.stream()
                .filter(task -> !task.getName().equals(name))
                .collect(Collectors.toList());
        deleteFile(PATH);
        writeFile(GSON.toJson(tasks), PATH);
    }


    public Task getTask(String name){
        List<Task> taskList = getTasks();
        for (Task task : taskList)
            if (task.getName().equals(name)) {
                return task;
            }
        throw new NoSuchElementException();
    }

}
