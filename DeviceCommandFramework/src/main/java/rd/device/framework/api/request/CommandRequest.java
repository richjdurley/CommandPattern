package rd.device.framework.api.request;


import rd.device.framework.api.handler.ifvalidrequestthen.ValidatableRequest;
import rd.device.framework.domain.Command;
import rd.device.framework.domain.CommandBuilder;

import java.util.Map;

/**
 * Abstract Command to be extended by user defined Items
 *
 * @author Xio
 */
public class CommandRequest extends BaseRequest implements ValidatableRequest {

    public CommandRequest() {
    }

    public CommandRequest(String requestId, Map<String, Object> headers, String correlationId, String dataContentType, String type, String name, String payload) {
        super(requestId, headers, correlationId, dataContentType, type, name, payload);
    }

    @Override
    public boolean validate() {
        boolean valid = true;

        if (this.getName().isEmpty()) {
            valid = false;
        }

        if (this.getType().isEmpty()) {
            valid = false;
        }

        return valid;
    }

    public Command asCommand() {
        return CommandBuilder.builder().withCommandId(this.getRequestId()).withCommandType(this.getType()).withCommandName(this.getName()).withCommandTimestamp(System.currentTimeMillis()).build();
    }

}
