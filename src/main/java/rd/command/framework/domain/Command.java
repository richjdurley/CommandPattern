package rd.command.framework.domain;

import java.util.UUID;

public abstract class Command<Result> {

  String commandID;

  CommandResult<Result> commandResult;

  public Command() {
    this.commandID = UUID.randomUUID().toString();
  }

  public void commandSucceeded(Result result) {
    commandResult = new CommandResult<Result>();
    commandResult.setResult(result);
    commandResult.setStatus(CommandStatus.SUCCESS);
  }

  public void commandSucceeded() {
    commandResult.setStatus(CommandStatus.SUCCESS);
  }

  public void commandFailed(CommandException commandException) {
    commandResult = new CommandResult<Result>();
    commandResult.setStatus(CommandStatus.FAILED);
    commandResult.setException(commandException);
  }

  public CommandResult<Result> getCommandResult() {
    return commandResult;
  }

  public void setCommandResult(CommandResult<Result> commandResult) {
    this.commandResult = commandResult;
  }

  public String getCommandID() {
    return commandID;
  }
}
