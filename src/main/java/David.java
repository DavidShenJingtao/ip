import java.util.Scanner;

public class David {
    public static void main(String[] args) {
        //welcome page
        String newline = "----------------------------------------";
        String welcome = newline + "\nHello! I'm David.\n"
                    + "What can I do for you?\n\n" + newline + "\n";
        System.out.println(welcome.indent(4));

        //user input
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.equals("bye")) {
            String msg = newline + "\n" + s
                        + "\n\n" + newline + "\n";
            System.out.println(msg.indent(4));
            s = sc.nextLine();
        }
        String bye = newline + "\nBye. Hope to see you again soon!\n\n"
                    + newline + "\n";
        System.out.println(bye.indent(4));
        sc.close();
    }
}
