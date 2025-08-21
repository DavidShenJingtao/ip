import java.util.Scanner;

public class David {
    private static final String s = "_";
    private static final String newline = s.repeat(60);
    private static int totalTasks = 0;

    public static void printList(String[] list) {
        System.out.print(newline.indent(4));
        for (int i = 0; i < totalTasks; i++) {
            String msg = String.format(" %d. %s", i + 1, list[i]);
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
        String[] list = new String[100];
        int i = 0;
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                printList(list);
            } else {
                list[i] = s;
                i++;
                totalTasks++;
                String msg = newline + "\n added: " + s
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
