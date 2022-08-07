package rd.device.framework.domain;

import org.springframework.http.MediaType;

import java.util.UUID;

public final class CommandBuilder {
    private String targetResourceURI;
    private String commandName;
    private String commandType;
    private String commandPayload;
    private String commandId;
    private long commandTimestamp;
    private long commandExpiryMilliseconds = Command.DEFAULT_EXPIRY_MILLISECONDS;

    private CommandBuilder() {
        this.commandPayload = null;
        this.commandTimestamp = System.currentTimeMillis();
        this.commandId = UUID.randomUUID().toString();
    }

    private CommandBuilder(String payload) {
        super();
        this.commandPayload = payload;
    }

    public static CommandBuilder builder(String payload) {
        return new CommandBuilder(payload);
    }

    public static CommandBuilder builder() {
        return new CommandBuilder();
    }

    public CommandBuilder withTargetResourceURI(String targetResourceURI) {
        this.targetResourceURI = targetResourceURI;
        return this;
    }

    public CommandBuilder withCommandName(String commandName) {
        this.commandName = commandName;
        return this;
    }

    public CommandBuilder withCommandType(String commandType) {
        this.commandType = commandType;
        return this;
    }

    public CommandBuilder withCommandId(String commandId) {
        this.commandId = commandId;
        return this;
    }

    public CommandBuilder withCommandTimestamp(long commandTimestamp) {
        this.commandTimestamp = commandTimestamp;
        return this;
    }

    public CommandBuilder withCommandExpiryMilliseconds(long commandExpiryMilliseconds) {
        this.commandExpiryMilliseconds = commandExpiryMilliseconds;
        return this;
    }

    public Command build() {
        if (commandPayload != null)
            return new Command(this.targetResourceURI, this.commandName, this.commandType, this.commandPayload, MediaType.APPLICATION_JSON_VALUE,
                    this.commandId, this.commandTimestamp, this.commandExpiryMilliseconds);
        else
            return new Command(this.targetResourceURI, this.commandName, this.commandType, "{}", MediaType.APPLICATION_JSON_VALUE, this.commandId, this.commandTimestamp, this.commandExpiryMilliseconds);
    }
}
