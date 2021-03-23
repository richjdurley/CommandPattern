package rd.command.components.api.framework.exceptions;

public class PathNotFoundException extends RuntimeException {
    public PathNotFoundException(String message) {
        super(message);
    }
}
