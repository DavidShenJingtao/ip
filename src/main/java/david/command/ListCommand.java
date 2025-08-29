package david.command;

import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}