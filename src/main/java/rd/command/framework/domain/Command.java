package rd.command.framework.domain;

import java.util.UUID;

public class Command<Data> {

  String commandID;
  String commandName;
  Data commandData;

  public Command() {
    commandID = UUID.randomUUID().toString();
  }

  public Command(String commandName) {
    this.commandName = commandName;
  }

  public Command(String commandName, Data commandData) {
    this.commandName = commandName;
    this.commandData = commandData;
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
}
