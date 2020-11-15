package rd.command.framework.domain;

import java.util.UUID;

public final class CommandBuilder<C> {
    private String commandName;
    private Object commandPayload=EmptyPayload.PAYLOAD;
    private String commandUUID= UUID.randomUUID().toString();
    private long commandTimestamp = System.currentTimeMillis();
    private long commandExpiryMilliseconds = 3000;

    private CommandBuilder() {
    }

    public static <C> CommandBuilder<C> builder() { return new CommandBuilder<C>();};

    public CommandBuilder<C> withCommandName(String commandName) {
        this.commandName = commandName;
        return this;
    }

    public CommandBuilder<C> withCommandPayload(C commandPayload) {
        this.commandPayload = commandPayload;
        return this;
    }

    public CommandBuilder<C> withCommandUUID(String commandUUID) {
        this.commandUUID = commandUUID;
        return this;
    }

    public CommandBuilder<C> withCommandTimestamp(long commandTimestamp) {
        this.commandTimestamp = commandTimestamp;
        return this;
    }

    public CommandBuilder<C> withCommandExpiryMilliseconds(long commandExpiryMilliseconds) {
        this.commandExpiryMilliseconds = commandExpiryMilliseconds;
        return this;
    }

    public Command<C> build() {
        return new Command(commandName, commandPayload, commandUUID, commandTimestamp, commandExpiryMilliseconds);
    }
}
