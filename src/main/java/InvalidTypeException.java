public class InvalidTypeException extends DavidException {
    public InvalidTypeException(String type) {
        super("Error: the type " + type + " you entered is invalid.");
    }
}