public class InvalidTypeException extends DavidException {
    public InvalidTypeException(String type) {
        super("the type " + type + " you entered is invalid.");
    }
}