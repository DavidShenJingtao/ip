public class IndexException extends DavidException {
    public IndexException(String msg) {
        super(msg + " is out of bound.");
    }
}