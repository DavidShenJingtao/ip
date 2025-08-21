public class Event extends Task {
    private static final String type = "E";

    public Event(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", type, super.toString());
    }
}