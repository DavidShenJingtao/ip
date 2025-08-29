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