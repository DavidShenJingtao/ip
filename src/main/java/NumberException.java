public class NumberException extends DavidException {
    public NumberException(String msg) {
        super("Error: " + msg + " is not a valid integer");
    }
}