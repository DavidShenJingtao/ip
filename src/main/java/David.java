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
                Task t = new Task(s);
                list.add(t);
                totalTasks++;
                String msg = newline + "\n added: " + t
                        + "\n" + newline + "\n";
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