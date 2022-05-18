package ui;

import org.junit.jupiter.api.Test;

public class ConsoleTest {

    static Console console = new Console();

    @Test
    public void testPrintAll() {
        console.printAll();
    }

    @Test
    public void testRemoveTask(){
        console.removeTask();
    }

    public static void main(String[] args) {
        console.removeTask();
    }

}
