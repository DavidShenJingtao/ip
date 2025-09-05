package david.command;

import david.exception.DavidException;
import david.exception.IndexException;
import david.exception.NumberException;
import david.task.Task;
import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

/**
 * Marks a specific task as 'undone' given the index from the command.
 */
public class UnmarkCommand extends Command {
    private String command;

    /**
     * @param command The entire unmark command.
     */
    public UnmarkCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        String[] strarr = this.command.split(" ");
        if (strarr.length <= 1 || strarr.length > 2 || !isInteger(strarr[1])) {
            throw new NumberException("the value you entered after unmark");
        }
        int index = Integer.valueOf(strarr[1]) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new IndexException("the value you entered after unmark");
        }
        Task t = tasks.get(index);
        t.markAsUndone();
        storage.save(tasks);

        String msg = "OK, I've marked this task as not done yet:\n  " + t;
        ui.showMessage(msg);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        String[] strarr = this.command.split(" ");
        if (strarr.length <= 1 || strarr.length > 2 || !isInteger(strarr[1])) {
            throw new NumberException("the value you entered after unmark");
        }
        int index = Integer.valueOf(strarr[1]) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new IndexException("the value you entered after unmark");
        }
        Task t = tasks.get(index);
        t.markAsUndone();
        storage.save(tasks);

        String msg = "OK, I've marked this task as not done yet:\n  " + t;
        return msg;
    }

    private static boolean isInteger(String s) {
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
}
