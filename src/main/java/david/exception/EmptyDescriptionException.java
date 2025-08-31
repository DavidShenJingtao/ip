package david.exception;

public class EmptyDescriptionException extends DavidException {
    public EmptyDescriptionException(String type) {
        super("the description of " + type + " cannot be empty.");
    }
}