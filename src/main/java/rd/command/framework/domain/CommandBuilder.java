package rd.command.framework.domain;

import java.util.Optional;
import java.util.UUID;

public final class CommandBuilder {
    private String targetResourceURI;
    private String commandName;
    private String commandType;
    private Object commandPayload;
    private String commandId;
    private long commandTimestamp;
    private long commandExpiryMilliseconds = Command.DEFAULT_EXPIRY_MILLISECONDS;

    private CommandBuilder() {
        this.commandPayload=null;
        this.commandTimestamp= System.currentTimeMillis();
        this.commandId=UUID.randomUUID().toString();
    }

    private CommandBuilder(Object payload) {
        super();
        this.commandPayload = payload;
    }

    public static <P> CommandBuilder builder(P payload) {
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
        if (commandPayload!=null)
            return new Command(this.targetResourceURI, this.commandName, this.commandType, Optional.of(this.commandPayload), this.commandId, this.commandTimestamp, this.commandExpiryMilliseconds);
        else
            return new Command(this.targetResourceURI, this.commandName, this.commandType, Optional.empty(), this.commandId, this.commandTimestamp, this.commandExpiryMilliseconds);
    }
}
