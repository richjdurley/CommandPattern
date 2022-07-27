package rd.command.app.devicecontroller.domain.command;

import rd.command.framework.domain.Command;

import java.util.Optional;

public class LightCommand extends Command {
    protected LightCommand(String targetResourceURI, String name, String type, Optional payload, String id, long createdTimestamp, long expiryMilliseconds) {
        super(targetResourceURI, name, type, payload, id, createdTimestamp, expiryMilliseconds);
    }
}
