public class EmptyDescriptionException extends DavidException {
    public EmptyDescriptionException(String type) {

        super("Error: the description of " + type + " cannot be empty.");
    }
}