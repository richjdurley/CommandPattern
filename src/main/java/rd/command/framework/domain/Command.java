package rd.command.framework.domain;

import java.util.Date;
import java.util.UUID;

public class Command<Data> {

    String commandID;
    String commandName;
    Data commandData;
    Date createdTimestamp;

    public Command() {
        commandID = UUID.randomUUID().toString();
        createdTimestamp = new Date(System.currentTimeMillis());
    }

    public Command(String commandName) {
        commandID = UUID.randomUUID().toString();
        this.commandName = commandName;
        createdTimestamp = new Date(System.currentTimeMillis());
    }

    public Command(String commandName, Data commandData) {
        commandID = UUID.randomUUID().toString();
        this.commandName = commandName;
        this.commandData = commandData;
        createdTimestamp = new Date(System.currentTimeMillis());
    }

    public String getCommandID() {
        return commandID;
    }

    public String getCommandName() {
        return commandName;
    }

    public Data getCommandData() {
        return commandData;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
}
