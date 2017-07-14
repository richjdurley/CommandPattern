package rd.command.framework.domain;

import java.util.UUID;

public abstract class Command<Result> {

  String commandID;
  String commandName;

  CommandResult<Result> commandResult;

  public Command() {
    this.commandID = UUID.randomUUID().toString();
    commandResult = new CommandResult<>();
  }

  public Command(String commandName) {
    super();
    this.commandName = commandName;
  }

  public String getCommandID() {
    return commandID;
  }

  public void commandSucceeded() {
    commandSucceeded(null);
  }

  public void commandSucceeded(Result result) {
    commandResult.setStatus(CommandStatus.SUCCESS);
    commandResult.setResult(result);
  }

  public void commandFailed() {
    commandFailed(new CommandException());
  }

  public void commandFailed(CommandException commandException) {
    commandResult.setStatus(CommandStatus.FAILED);
    commandResult.setException(commandException);
  }

  public CommandResult<Result> getCommandResult() {
    return commandResult;
  }

  public String getCommandName() {
    return commandName;
  }
}
