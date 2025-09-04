package david.command;

import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

/**
 * Exits the execution and prints farewell message.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Overrides isExit() from the parent class.
     *
     * @return true because it is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
