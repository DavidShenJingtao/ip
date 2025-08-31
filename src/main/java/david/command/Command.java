package david.command;

import david.exception.DavidException;
import david.exception.SaveException;
import david.task.Deadline;
import david.task.Event;
import david.task.Task;
import david.task.ToDo;
import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui,
                                    Storage storage) throws DavidException;

    public boolean isExit() {
        return false;
    }
}