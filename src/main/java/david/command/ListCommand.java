package david.command;

import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

/**
 * Prints all the tasks from the list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        tasks.printList();
    }
}
