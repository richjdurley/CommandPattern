package rd.command.framework.api.exceptions;

public class EmptyRequestException extends BadRequestException {
    public EmptyRequestException() {
        super("Invalid Request : Cannot be empty");
    }
}
