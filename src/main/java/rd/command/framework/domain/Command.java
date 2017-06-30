package rd.command.framework.domain;

import java.util.UUID;

public abstract class Command<Result> {

  String commandID;

  CommandResult<Result> commandResult;

  public Command() {
    this.commandID = UUID.randomUUID().toString();
    commandResult = new CommandResult<>();
  }

  public String getCommandID() {
    return commandID;
  }

  public void commandSucceeded() {
    commandResult.setStatus(CommandStatus.SUCCESS);
  }

  public void commandSucceeded(Result result) {
    commandResult.setResult(result);
    commandResult.setStatus(CommandStatus.SUCCESS);
  }

  public void commandFailed() {
    this.commandResult.setStatus(CommandStatus.FAILED);
  }

  public void commandFailed(CommandException commandException) {
    commandResult.setStatus(CommandStatus.FAILED);
    commandResult.setException(commandException);
  }

  public CommandResult<Result> getCommandResult() {
    return commandResult;
  }

  public void setCommandResult(CommandResult<Result> commandResult) {
    this.commandResult = commandResult;
  }
}
