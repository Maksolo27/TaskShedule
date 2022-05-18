import ui.Commands;
import ui.Console;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Console console = new ui.Console();
    private static boolean isRunning = true;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        while (isRunning) {
            System.out.println("Please input your command");
            scanner = new Scanner(System.in);
            String commandString = scanner.nextLine();
            Commands command = Commands.getCommand(commandString);
            switch (command) {
                case ADD_TASK -> console.addTask();
                case PRINT_ALL -> console.printAll();
                case REMOVE_TASK -> console.removeTask();
                case UPDATE_TASK -> console.updateTask();
                case MARK_AS_COMPLETED -> console.maksAsCompleted();
                case HELP -> console.printAllCommands();
                case PRINT_COMPLETED -> console.printCompletedTasks();
                case END_APP -> isRunning = false;
                default -> throw new UnsupportedOperationException("This command is unsupported");
            }
        }
    }

}
