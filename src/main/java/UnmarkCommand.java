public class UnmarkCommand extends Command {
    private String command;

    public UnmarkCommand(String command) {
        super();
        this.command = command;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DavidException {
        String[] strarr = this.command.split(" ");
        if (strarr.length <= 1 || strarr.length > 2 || !isInteger(strarr[1])) {
            throw new NumberException("the value you entered after unmark");
        }
        int index = Integer.valueOf(strarr[1]) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new IndexException("the value you entered after unmark");
        }
        Task t = tasks.get(index);
        t.markAsUndone();
        storage.save(tasks);

        String msg = "OK, I've marked this task as not done yet:\n  " + t;
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