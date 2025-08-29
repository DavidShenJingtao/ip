package david.task;

public class ToDo extends Task {
    private static final String type = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("%s | %s", type, super.toString());
    }
}