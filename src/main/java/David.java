import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class David {
    private static final String s = "_";
    private static final String newline = s.repeat(100);
    private static int totalTasks = 0;
    private static ArrayList<Task> list = new ArrayList<>();
    private static Storage storage = new Storage(Paths.get("data",
                                                "David.txt").toString());

    //helper function: check if integer
    public static boolean isInteger(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //helper function: format the output
    public static String format(String s) {
        String msg = newline + "\n " + s + "\n" + newline + "\n";
        return msg.indent(4);
    }

    public static void printList() {
        String start = newline + "\n Here are the tasks in your list: ";
        System.out.print(start.indent(4));
        for (int i = 0; i < list.size(); i++) {
            String msg = String.format(" %d. %s", i + 1, list.get(i));
            System.out.print(msg.indent(4));
        }
        System.out.print(newline.indent(4));
        System.out.println();
    }

    public static void mark(String s) throws DavidException {
        String[] strarr = s.split(" ");
        if (strarr.length <= 1 || strarr.length > 2 || !isInteger(strarr[1])) {
            throw new NumberException("the value you entered after mark");
        }
        int index = Integer.parseInt(strarr[1]) - 1;
        if (index < 0 || index > list.size() - 1) {
            throw new IndexException("the value you entered after mark");
        }
        Task t = list.get(index);
        t.markAsDone();
        String msg = "Nice! I've mark this task as done:\n  " + t;
        System.out.println(format(msg));
    }

    public static void unmark(String s) throws DavidException {
        String[] strarr = s.split(" ");
        if (strarr.length <= 1 || strarr.length > 2 || !isInteger(strarr[1])) {
            throw new NumberException("the value you entered after unmark");
        }
        int index = Integer.valueOf(strarr[1]) - 1;
        if (index < 0 || index > list.size() - 1) {
            throw new IndexException("the value you entered after unmark");
        }
        Task t = list.get(index);
        t.markAsUndone();
        String msg = "OK, I've marked this task as not done yet:\n  " + t;
        System.out.println(format(msg));
    }

    public static void delete(String s) throws DavidException {
        String[] strarr = s.split(" ");
        if (strarr.length <= 1 || strarr.length > 2 || !isInteger(strarr[1])) {
            throw new NumberException("the value you entered after delete");
        }
        int index = Integer.valueOf(strarr[1]) - 1;
        if (index < 0 || index > list.size() - 1) {
            throw new IndexException("the value you entered after delete");
        }
        Task t = list.get(index);
        list.remove(index);
        totalTasks--;
        String task = (totalTasks > 1) ? "tasks" : "task";
        String msg = "Noted, I've removed this task:\n  " + t
                + "\n Now you have " + totalTasks + " "
                + task + " in the list.";
        System.out.println(format(msg));
    }

    public static void main(String[] args) {
        //initialize storage
        try {
            storage.init();
            list = storage.load();
            totalTasks = list.size();
        } catch (IOException e) {
            System.out.println(format(e.getMessage()));
        }

        //welcome page
        String welcome = "Hello! I'm David.\n What can I do for you?";
        System.out.println(format(welcome));

        //user input
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit && sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] strarr = s.split(" ", 2);
            if (s.equals("bye")) {
                isExit = true;
                String bye = "Bye. Hope to see you again soon!";
                System.out.println(format(bye));
            } else if (s.equals("list")) {
                printList();

            } else if (strarr[0].equals("mark")) {
                try {
                    mark(s);
                    saveTasks();
                } catch (DavidException e) {
                    System.out.println(format(e.getMessage()));
                }

            } else if (strarr[0].equals("unmark")) {
                try {
                    unmark(s);
                    saveTasks();
                } catch (DavidException e) {
                    System.out.println(format(e.getMessage()));
                }

            } else if (strarr[0].equals("delete")) {
                try {
                    delete(s);
                    saveTasks();
                } catch (DavidException e) {
                    System.out.println(format(e.getMessage()));
                }

            } else {
                try {
                    Task t = Task.of(s);
                    list.add(t);
                    totalTasks++;
                    saveTasks();
                    String task = (totalTasks > 1) ? "tasks" : "task";
                    String msg = "Got it. I've added this task:\n  "
                            + t + "\n Now you have " + totalTasks + " "
                            + task + " in the list.";
                    System.out.println(format(msg));
                } catch (DavidException e) {
                    System.out.println(format(e.getMessage()));
                }
            }
        }
        sc.close();
    }

    private static void saveTasks() {
        try {
            storage.save(list);
        } catch (IOException e) {
            System.out.println(format("Error: " + e.getMessage()));
        }
    }
}