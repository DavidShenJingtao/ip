package david.ui;

import david.command.AddCommand;
import david.command.Command;
import david.command.DeleteCommand;
import david.command.ExitCommand;
import david.command.FindCommand;
import david.command.ListCommand;
import david.command.MarkCommand;
import david.command.UnmarkCommand;
import david.exception.DavidException;
import david.exception.EmptyDescriptionException;
import david.exception.FormatException;
import david.exception.IndexException;
import david.exception.InvalidTypeException;
import david.exception.NumberException;
import david.exception.SaveException;

/**
 * Makes sense of the command line.
 */
public class Parser {

     /**
     * @param command The whole command.
     * @return A command object for subsequent execution.
     * @throws DavidException If parsing the string fails.
     */
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

        case "find":
            return new FindCommand(command);

        default:
            return new AddCommand(command);
        }
    }
}
