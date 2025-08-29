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