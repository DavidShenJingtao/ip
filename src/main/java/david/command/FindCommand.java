package david.command;

import david.exception.DavidException;
import david.exception.EmptyDescriptionException;
import david.task.Deadline;
import david.task.Event;
import david.task.Task;
import david.task.ToDo;
import david.ui.Storage;
import david.ui.TaskList;
import david.ui.Ui;
import java.util.ArrayList;

public class FindCommand extends Command {
    private String command;

    public FindCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        TaskList res = new TaskList();
        String[] strArr = this.command.split(" ");
        if (strArr.length <= 1) {
            throw new EmptyDescriptionException("find command");
        }
        String key = strArr[1];
        ArrayList<Task> list = tasks.getList();
        for (Task t : list) {
            String description = t.getDescription();
            if (description.contains(key)) {
                res.add(t);
            }
        }
        res.printMatchList();
    }
}