import java.util.ArrayList;
import java.util.Scanner;

public class David {
    private static final String s = "_";
    private static final String newline = s.repeat(100);
    private static int totalTasks = 0;
    private static ArrayList<Task> list = new ArrayList<>();

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
        for (int i = 0; i < totalTasks; i++) {
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

    public static Task parseTask(String[] strarr) throws DavidException {
        String type = strarr[0];
        if (!type.equals("todo") && !type.equals("deadline")
                && !type.equals("event")) {
            throw new InvalidTypeException(type);
        }
        if (strarr.length <= 1) {
            throw new EmptyDescriptionException(type);
        }
        String description;
        switch (type) {
            case "todo":
                description = strarr[1];
                return new ToDo(description);
            case "deadline":
                String[] by = strarr[1].split(" /by ", 2);
                if (by.length < 2) {
                    String m = "the correct format of deadline should be: " +
                            "deadline [task name] /by [time]";
                    throw new FormatException(m);
                }
                description = by[0] + " (by: " + by[1] + ")";
                return new Deadline(description);
            case "event":
                String m = "the correct format of event should be: " +
                        "event [task name] /from [start time] /to [end time]";
                String[] from = strarr[1].split(" /from ", 2);
                if (from.length < 2) {
                    throw new FormatException(m);
                }
                String[] to = from[1].split(" /to ", 2);
                if (to.length < 2) {
                    throw new FormatException(m);
                }
                description = from[0] + " (from: "
                            + to[0] + " to: " + to[1] + ")";
                return new Event(description);
            default:
                throw new InvalidTypeException(type);
        }
    }

    public static void main(String[] args) {
        //welcome page
        String welcome = "Hello! I'm David.\n What can I do for you?";
        System.out.println(format(welcome));
        //user input
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        while (!s.equals("bye")) {
            String[] strarr = s.split(" ", 2);
            if (s.equals("list")) {
                printList();

            } else if (strarr[0].equals("mark")) {
                try {
                    mark(s);
                } catch (NumberException e1) {
                    System.out.println(format(e1.getMessage()));
                } catch (IndexException e2) {
                    System.out.println(format(e2.getMessage()));
                } catch (DavidException e) {
                    System.out.println(format(e.getMessage()));
                }

            } else if (strarr[0].equals("unmark")) {
                try {
                    unmark(s);
                } catch (NumberException e1) {
                    System.out.println(format(e1.getMessage()));
                } catch (IndexException e2) {
                    System.out.println(format(e2.getMessage()));
                } catch (DavidException e) {
                    System.out.println(format(e.getMessage()));
                }

            } else {
                try {
                    Task t = parseTask(strarr);
                    list.add(t);
                    totalTasks++;
                    String task = (totalTasks > 1) ? "tasks" : "task";
                    String msg = "Got it. I've added this task:\n  "
                            + t + "\n Now you have " + totalTasks + " "
                            + task + " in the list.";
                    System.out.println(format(msg));
                } catch (InvalidTypeException e1) {
                    System.out.println(format(e1.getMessage()));
                } catch (EmptyDescriptionException e2) {
                    System.out.println(format(e2.getMessage()));
                } catch (FormatException e3) {
                    System.out.println(format(e3.getMessage()));
                } catch (DavidException e) {
                    System.out.println(format(e.getMessage()));
                }
            }
            s = sc.nextLine();
        }
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(format(bye));
        sc.close();
    }
}