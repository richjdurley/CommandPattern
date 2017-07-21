package rd.command.framework.domain;

import java.util.UUID;

public class CommandRequest<P> {
  String commandID;
  String commandName;
  P commandData;

  public CommandRequest() {
    this.commandID = UUID.randomUUID().toString();
  }

  public String getCommandName() {
    return commandName;
  }

  public String getCommandID() {
    return commandID;
  }

  public P getCommandData() {
    return commandData;
  }
}
