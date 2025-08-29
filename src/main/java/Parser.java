public class Parser {
    public static Command parse(String command) throws DavidException {
        String[] strarr = command.split(" ", 2);
        String op = strarr[0];

        switch (op) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(command);

        case "unmark":
            return new UnmarkCommand(command);

        case "delete":
            return new DeleteCommand(command);

        default:
            return new AddCommand(command);
        }
    }
}
