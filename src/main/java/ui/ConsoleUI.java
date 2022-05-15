package ui;

import db.Task;
import lombok.SneakyThrows;
import services.TaskService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsoleUI {

    private final TaskService taskService = new TaskService();
    private Scanner scanner = new Scanner(System.in);

    @SneakyThrows
    public void addTask(){
        System.out.println("Please add you task");
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
        taskService.addTask(task);

    }
    private void updateScanner(){
        scanner = new Scanner(System.in);
    }
}
