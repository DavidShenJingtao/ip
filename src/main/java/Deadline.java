public class Deadline extends Task {
    private static final String type = "D";

    public Deadline(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", type, super.toString());
    }
}