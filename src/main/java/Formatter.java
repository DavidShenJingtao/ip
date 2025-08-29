public class Formatter {
    private static final String s = "_";
    public static final String NEWLINE = s.repeat(100);

    /**
     * Formats the output.
     * 
     * @param s
     * @return formmatted output.
     */
    public static String format(String output) {
        String msg = NEWLINE + "\n " + output + "\n" + NEWLINE + "\n";
        return msg.indent(4);
    }
}