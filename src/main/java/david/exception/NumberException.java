package david.exception;

public class NumberException extends DavidException {
    public NumberException(String msg) {
        super(msg + " is not a valid integer.");
    }
}