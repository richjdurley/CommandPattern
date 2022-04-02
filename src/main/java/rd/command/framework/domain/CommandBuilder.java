package rd.command.framework.domain;

import java.util.Optional;
import java.util.UUID;

public final class CommandBuilder<P> {
    private String targetResourceURI;
    private String commandName;
    private String commandType;
    private Optional<P> commandPayload = Optional.empty();
    private String commandId = UUID.randomUUID().toString();
    private long commandTimestamp = System.currentTimeMillis();
    private long commandExpiryMilliseconds = Command.DEFAULT_EXPIRY_MILLISECONDS;

    private CommandBuilder() {
    }

    public static <P> CommandBuilder<P> builder() {
        return new CommandBuilder<>();
    }

    public CommandBuilder<P> withTargetResourceURI(String targetResourceURI) {
        this.targetResourceURI = targetResourceURI;
        return this;
    }

    public CommandBuilder<P> withCommandName(String commandName) {
        this.commandName = commandName;
        return this;
    }

    public CommandBuilder<P> withCommandType(String commandType) {
        this.commandType = commandType;
        return this;
    }

    public CommandBuilder<P> withCommandPayload(P commandPayload) {
        this.commandPayload = Optional.of(commandPayload);
        return this;
    }

    public CommandBuilder<P> withCommandId(String commandId) {
        this.commandId = commandId;
        return this;
    }

    public CommandBuilder<P> withCommandTimestamp(long commandTimestamp) {
        this.commandTimestamp = commandTimestamp;
        return this;
    }

    public CommandBuilder<P> withCommandExpiryMilliseconds(long commandExpiryMilliseconds) {
        this.commandExpiryMilliseconds = commandExpiryMilliseconds;
        return this;
    }

    public Command<P> build() {
        return new Command(this.targetResourceURI, this.commandName, this.commandType, this.commandPayload, this.commandId, this.commandTimestamp, this.commandExpiryMilliseconds);
    }
}
