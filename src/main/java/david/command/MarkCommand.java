package david.command;

import david.exception.DavidException;
import david.exception.IndexException;
import david.exception.NumberException;
import david.exception.SaveException;
import david.task.Deadline;
import david.task.Event;
import david.task.Task;
import david.task.ToDo;
import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

public class MarkCommand extends Command {
    private String command;

    public MarkCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        String[] strarr = this.command.split(" ");
        if (strarr.length <= 1 || strarr.length > 2 || !isInteger(strarr[1])) {
            throw new NumberException("the value you entered after mark");
        }
        int index = Integer.parseInt(strarr[1]) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new IndexException("the value you entered after mark");
        }
        Task t = tasks.get(index);
        t.markAsDone();
        storage.save(tasks);

        String msg = "Nice! I've mark this task as done:\n  " + t;
        ui.showMessage(msg);
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