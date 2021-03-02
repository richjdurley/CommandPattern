package rd.command.framework.domain;

import java.util.UUID;

public final class CommandBuilder<P> {
    private String targetDeviceName;
    private String commandActionName;
    private P commandActionPayload;
    private String commandUUID= UUID.randomUUID().toString();
    private long commandTimestamp = System.currentTimeMillis();
    private long commandExpiryMilliseconds = Command.DEFAULT_EXPIRY_MILLISECONDS;
    
    private CommandBuilder() {
    }

    public static <P> CommandBuilder<P> builder() { return new CommandBuilder<>();};

    public CommandBuilder<P> withTargetDeviceName(String targetDeviceName) {
        this.targetDeviceName = targetDeviceName;
        return this;
    }

    public CommandBuilder<P> withCommandActionName(String commandActionName) {
        this.commandActionName = commandActionName;
        return this;
    }
    
    public CommandBuilder<P> withCommandPayload(P commandPayload) {
        this.commandActionPayload = commandPayload;
        return this;
    }

    public CommandBuilder<P> withCommandUUID(String commandUUID) {
        this.commandUUID = commandUUID;
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
        return new Command<P>(this.targetDeviceName, this.commandActionName, this.commandActionPayload, this.commandUUID, this.commandTimestamp, this.commandExpiryMilliseconds);
    }
}
