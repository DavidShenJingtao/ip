package david.task;

/**
 * A simple task that has no time attributes.
 */
public class ToDo extends Task {
    private static final String type = "T";

    /**
     * @param description Description of the task todo.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("%s | %s", type, super.toString());
    }
}
