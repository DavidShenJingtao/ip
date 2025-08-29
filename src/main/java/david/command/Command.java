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
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui User interface for displaying messages.
     * @param storage Save changes in the list.
     * @throws DavidException if something goes wrong.
     */
    public abstract void execute(TaskList tasks, Ui ui,
                                    Storage storage) throws DavidException;

    /**
     * Checks whether the command will exit the program.
     */
    public boolean isExit() {
        return false;
    }

}