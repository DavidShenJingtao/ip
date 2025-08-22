public class IndexException extends DavidException {
    public IndexException(String msg) {
        super("Error: " + msg + " is out of bound");
    }
}