import java.util.ArrayList;
import java.util.Scanner;

public class David {
    private static final String s = "_";
    private static final String newline = s.repeat(60);
    private static int totalTasks = 0;

    public static void printList(ArrayList<? extends Task> list) {
        String start = newline + "\n Here are the tasks in your list: ";
        System.out.print(start.indent(4));
        for (int i = 0; i < totalTasks; i++) {
            String msg = String.format(" %d. %s", i + 1, list.get(i));
            System.out.print(msg.indent(4));
        }
        System.out.print(newline.indent(4));
        System.out.println();
    }

    public static Task parseTask(String[] strarr) {
        String type = strarr[0];
        String description;

        switch (type) {
            case "todo":
                description = strarr[1];
                return new ToDo(description);
            case "deadline":
                String[] by = strarr[1].split("/by", 2);
                description = by[0] + "(by:" + by[1] + ")";
                return new Deadline(description);
            case "event":
                String[] from = strarr[1].split("/from", 2);
                String[] to = from[1].split("/to", 2);
                description = from[0] + "(from:"
                            + to[0] + "to:" + to[1] + ")";
                return new Event(description);
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        //welcome page
        String welcome = newline + "\n Hello! I'm David.\n" +
                    " What can I do for you?\n" + newline + "\n";
        System.out.println(welcome.indent(4));

        //user input
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        while (!s.equals("bye")) {
            String[] strarr = s.split(" ", 2);

            if (s.equals("list")) {
                printList(list);

            } else if (strarr[0].equals("mark")) {
                int index = Integer.valueOf(strarr[1]) - 1;
                Task t = list.get(index);
                t.markAsDone();
                String mark = newline
                        + "\n Nice! I've mark this task as done:\n  "
                        + t + "\n" + newline + "\n";
                System.out.println(mark.indent(4));

            } else if (strarr[0].equals("unmark")) {
                int index = Integer.valueOf(strarr[1]) - 1;
                Task t = list.get(index);
                t.markAsUndone();
                String unmark = newline
                        + "\n OK, I've marked this task as not done yet:\n  "
                        + t + "\n" + newline + "\n";
                System.out.println(unmark.indent(4));

            } else {
                Task t = parseTask(strarr);
                list.add(t);
                totalTasks++;
                String task = (totalTasks > 1) ? "tasks" : "task";
                String msg = newline + "\n Got it. I've added this task:\n  "
                        + t + "\n Now you have " + totalTasks + " "
                        + task + " in the list.\n" + newline + "\n";
                System.out.println(msg.indent(4));

            }
            s = sc.nextLine();
        }
        String bye = newline + "\n Bye. Hope to see you again soon!\n"
                    + newline + "\n";
        System.out.println(bye.indent(4));
        sc.close();
    }
}