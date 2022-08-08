package rd.device.framework.api.handler;


import rd.device.framework.api.handler.ifvalidrequestthen.ValidatableRequest;
import rd.device.framework.domain.Command;

/**
 * Abstract Command to be extended by user defined Items
 *
 * @author Xio
 */
public class CommandRequest implements ValidatableRequest {

    private String name;
    private String action;

    public CommandRequest(String name, String action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public boolean validate() {
        boolean valid = true;

        if (name.isEmpty()) {
            valid = false;
        }

        if (action.isEmpty()) {
            valid = false;
        }

        return valid;
    }

    public Command getCommand() {
        return new Command(name, action);
    }
}
