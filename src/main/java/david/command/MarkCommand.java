package david.command;

import david.exception.DavidException;
import david.exception.IndexException;
import david.exception.NumberException;
import david.task.Task;
import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

/**
 * Marks a specific task as 'done' given the index from the command.
 */
public class MarkCommand extends Command {
    private String command;

    /**
     * @param command The entire mark command.
     */
    public MarkCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        String[] strArr = this.command.split(" ");
        if (strArr.length != 2 || !isInteger(strArr[1])) {
            throw new NumberException("the value you entered after mark");
        }
        int index = Integer.parseInt(strArr[1]) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new IndexException("the value you entered after mark");
        }
        Task t = performMark(tasks, storage, index);
        ui.showMessage(buildMessage(t));
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        String[] strArr = this.command.split(" ");
        if (strArr.length != 2 || !isInteger(strArr[1])) {
            throw new NumberException("the value you entered after mark");
        }
        int index = Integer.parseInt(strArr[1]) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new IndexException("the value you entered after mark");
        }
        Task t = performMark(tasks, storage, index);
        return buildMessage(t);
    }

    private Task performMark(TaskList tasks, Storage storage, int index)
                                                        throws DavidException {
        Task t = tasks.get(index);
        t.markAsDone();
        storage.save(tasks);
        return t;
    }

    private String buildMessage(Task t) {
        return "Nice! I've mark this task as done:\n  " + t;
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
