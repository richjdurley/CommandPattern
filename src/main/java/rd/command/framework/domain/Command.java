package rd.command.framework.domain;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Command<Result> {

  String commandID;
  String commandName;

  CommandResult<Result> commandResult;

  public class CommandProgress {
    Long timestamp;
    CommandStatus commandStatus;
    Object progress;

    public CommandProgress(CommandStatus commandStatus) {
      this.commandStatus = commandStatus;
      this.timestamp = System.currentTimeMillis();
    }

    public CommandProgress(CommandStatus commandStatus, Object progress) {
      this.timestamp = System.currentTimeMillis();
      this.commandStatus = commandStatus;
      this.progress = progress;
    }

    public Long getTimestamp() {
      return timestamp;
    }

    public Object getProgress() {
      return progress;
    }

    public CommandStatus getCommandStatus() {
      return commandStatus;
    }
  }

  ArrayList<CommandProgress> progressLog = new ArrayList<>();

  public Command() {
    this.commandID = UUID.randomUUID().toString();
    commandResult = new CommandResult<>();
    commandResult.setStatus(CommandStatus.ACCEPTED);
    logProgress(CommandStatus.ACCEPTED);
  }

  public Command(String commandName) {
    super();
    this.commandName = commandName;
    commandResult.setStatus(CommandStatus.ACCEPTED);
    logProgress(CommandStatus.ACCEPTED);
  }

  public String getCommandID() {
    return commandID;
  }

  public void commandSucceeded() {
    commandSucceeded(null);
  }

  public void commandSucceeded(Result result) {
    commandResult.setStatus(CommandStatus.SUCCEEDED);
    commandResult.setResult(result);
    logProgress(CommandStatus.SUCCEEDED);
  }

  public void commandFailed() {
    commandFailed("UNDEFINED");
  }

  public void commandFailed(String failureReason) {
    commandResult.setStatus(CommandStatus.FAILED);
    commandResult.setFailedResult(new CommandFailedResult(failureReason));
    logProgress(CommandStatus.FAILED);
  }

  public void logProgress(CommandStatus commandStatus) {
    progressLog.add(new CommandProgress(commandStatus));
  }

  public void logProgress(Object someProgress) {
    progressLog.add(new CommandProgress(CommandStatus.INPROGRESS, someProgress));
  }

  public CommandResult<Result> getCommandResult() {
    return commandResult;
  }

  public ArrayList<CommandProgress> getProgressLog() {
    return progressLog;
  }

  public String getCommandName() {
    return this.getClass().getName();
  }
}
