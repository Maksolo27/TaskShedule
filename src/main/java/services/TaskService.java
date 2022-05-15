package services;

import com.google.gson.Gson;
import db.Task;
import lombok.SneakyThrows;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    private final Gson GSON = new Gson();
    private final EncryptService encryptService = new EncryptService();

    @SneakyThrows
    public List<Task> getTasks(){
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/taskList.json");
        Task[] tasks = GSON.fromJson(readFromInputStream(fileInputStream), Task[].class);
        return Arrays.stream(tasks).sorted(new TaskComparator()).collect(Collectors.toList());
    }

    private static class TaskComparator implements Comparator<Task>{

        @Override
        public int compare(Task task, Task t1) {
            return Long.compare(task.getCreatedOn().getTime(), t1.getCreatedOn().getTime());
        }
    }
    private String readFromInputStream(InputStream inputStream) throws IOException {
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
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/taskList.json");
        task.setPassword(encryptService.encryptPassword(task.getPassword()));
        Task[] tasks = GSON.fromJson(readFromInputStream(fileInputStream), Task[].class);
        GSON.toJson(Arrays.asList(tasks, task));
    }
}
