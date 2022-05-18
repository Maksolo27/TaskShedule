package ui;

import db.Task;
import de.vandermeer.asciitable.AsciiTable;
import lombok.SneakyThrows;
import services.EncryptService;
import services.TaskService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Console {

    private final TaskService taskService = new TaskService();
    private final EncryptService encryptService = new EncryptService();
    private Scanner scanner = new Scanner(System.in);

    @SneakyThrows
    public void addTask(){
        System.out.println("Please add your task");
        System.out.println("Input task name please");
        Task task = new Task();
        task.setName(scanner.nextLine());
        System.out.println("Input description");
        updateScanner();
        task.setDescription(scanner.nextLine());
        System.out.println("Input deadline date in format yyyy-MM-dd");
        updateScanner();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date deadLine = formatter.parse(scanner.nextLine());
        task.setDeadline(deadLine);
        System.out.println("Input your password please");
        String password = encryptService.encryptPassword(scanner.nextLine());
        task.setPassword(password);
        taskService.addTask(task);
    }

    public void printAll() {
        List<Task> taskList = taskService.getTasks();
        AsciiTable table = new AsciiTable();
        taskList.forEach(task -> {
            table.addRule();
            table.addRow(task.getName(), task.getDescription(), task.getDeadline(), String.format("Is active: %s", task.isActive()));
        });
        table.addRule();
        System.out.println(table.render());
    }

    public void removeTask(){
        System.out.println("Please input name of task which you want remove");
        updateScanner();
        String name = scanner.nextLine();
        System.out.println("Please input password for this task");
        updateScanner();
        String password = scanner.nextLine();
        encryptService.validatePassword(password, taskService.getTask(name).getPassword());
        taskService.removeTask(name);
    }

    @SneakyThrows
    public void updateTask(){
        System.out.println("Please input name of task, which you want to update");
        updateScanner();
        String oldName = scanner.nextLine();
        Task task = taskService.getTask(oldName);
        System.out.println("Please input password for this task");
        updateScanner();
        String password = scanner.nextLine();
        encryptService.validatePassword(password, taskService.getTask(oldName).getPassword());
        System.out.println(task);
        System.out.println("Input new name for this task");
        updateScanner();
        String name = scanner.nextLine();
        task.setName(name);
        System.out.println("Input new description");
        updateScanner();
        task.setDescription(scanner.nextLine());
        System.out.println("Input new deadline date in format yyyy-MM-dd");
        updateScanner();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date deadLine = formatter.parse(scanner.nextLine());
        task.setDeadline(deadLine);
        taskService.removeTask(oldName);
        taskService.addTask(task);
    }

    public void maksAsCompleted(){
        System.out.println("Input name of task, which you want mark as complted");
        updateScanner();
        Task task = taskService.getTask(scanner.nextLine());
        System.out.println("Please input password for this task");
        updateScanner();
        String password = scanner.nextLine();
        encryptService.validatePassword(password, task.getPassword());
        task.setActive(false);
        task.setCompletedOn(new Date());
    }

    public void printAllCommands(){
        Arrays.stream(Commands.values()).forEach(System.out::println);
    }

    public void printCompletedTasks(){
        List<Task> taskList = taskService.getCompletedTasks();
        AsciiTable table = new AsciiTable();
        taskList.forEach(task -> {
            table.addRule();
            table.addRow(task.getName(), task.getDescription(), task.getDeadline(), task.getCompletedOn());
        });
        table.addRule();
        System.out.println(table.render());
    }
    private void updateScanner(){
        scanner = new Scanner(System.in);
    }
}
