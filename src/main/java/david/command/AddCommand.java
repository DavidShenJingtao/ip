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

public class AddCommand extends Command {
    private String command;

    public AddCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        Task t = Task.of(command);
        tasks.add(t);
        storage.save(tasks);
        String task = (tasks.size() > 1) ? "tasks" : "task";
        String msg = "Got it. I've added this task:\n  "
                    + t + "\n Now you have " + tasks.size() + " "
                                                + task + " in the list.";
        ui.showMessage(msg);
    }

}