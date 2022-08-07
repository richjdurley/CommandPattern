package rd.device.framework.domain;


import rd.device.framework.api.controller.handler.ifvalidrequestthen.ValidatableRequest;
import rd.device.util.json.JSONUtil;

/**
 * Abstract Command to be extended by user defined Items
 *
 * @author Xio
 */
public class CommandRequest implements ValidatableRequest {

    private final Command command;

    public CommandRequest(Command command) {
        this.command = command;
    }

    @Override
    public boolean validate() {
        boolean valid = true;

        if (command.getId().isBlank()) {
            valid =false;
        }

        if (command.getType().isBlank()) {
            valid = false;
        }

        if (command.getDeviceResourceURI().isBlank()) {
            valid = false;
        }

        if (command.getName().isEmpty()) {
            valid = false;
        }

        if (command.getPayload() != null && !command.getPayload().isBlank()) {
            return JSONUtil.isJSONValid(command.getPayload());
        }

        return valid;
    }

    public Command getCommand() {
        return command;
    }
}
