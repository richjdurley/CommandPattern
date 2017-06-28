package rd.command.framework.domain;

public abstract class Command<Result> {

  CommandResult<Result> commandResult;

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
}
