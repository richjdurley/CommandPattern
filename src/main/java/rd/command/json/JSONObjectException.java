package rd.command.json;

public class JSONObjectException extends RuntimeException {
    public JSONObjectException(String message) {
        super(message);
    }

    public JSONObjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
