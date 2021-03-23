package rd.command.components.api.framework.exceptions;

public class EmptyRequestException extends BadRequestException {
    public EmptyRequestException() {
        super("Invalid Request : Cannot be empty");
    }
}
