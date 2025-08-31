package david.ui;

public class Formatter {
    private static final String s = "_";
    public static final String NEWLINE = s.repeat(100);

    public static String format(String output) {
        String msg = NEWLINE + "\n " + output + "\n" + NEWLINE + "\n";
        return msg.indent(4);
    }
}