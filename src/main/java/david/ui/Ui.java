package david.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        String welcome = "Hello! I'm David.\n What can I do for you?";
        System.out.println(Formatter.format(welcome));
    }

    public void showBye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(Formatter.format(bye));
    }

    public void showError(String msg) {
        System.out.println(Formatter.format("Error: " + msg));
    }

    public void showLoadingError() {
        System.out.println("Error: the tasks cannot be loaded.");
    }

    public void showMessage(String msg) {
        System.out.println(Formatter.format(msg));
    }

}
