public class Deadline extends Task {
    private static final String type = "D";
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", type, super.toString(), by);
    }
}