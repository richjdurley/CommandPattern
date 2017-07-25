package rd.command.framework.domain;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Command<Result> {

  String commandID;
  String commandName;

  CommandResponse<Result> commandResult;

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
    commandResult = new CommandResponse<>();
    commandResult.setStatus(CommandStatus.ACCEPTED);
    logMomento(CommandStatus.ACCEPTED);
  }

  public Command(String commandName) {
    super();
    this.commandName = commandName;
    commandResult.setStatus(CommandStatus.ACCEPTED);
    logMomento(CommandStatus.ACCEPTED);
  }

  public String getCommandID() {
    return commandID;
  }

  public void setSucceeded() {
    setSucceeded(null);
  }

  public void setSucceeded(Result result) {
    commandResult.setStatus(CommandStatus.SUCCEEDED);
    commandResult.setResult(result);
    logMomento(CommandStatus.SUCCEEDED);
  }

  public void setFailed() {
    setFailed("UNDEFINED");
  }

  public void setFailed(String failureReason) {
    commandResult.setStatus(CommandStatus.FAILED);
    commandResult.setFailedResult(new CommandFailedResult(failureReason));
    logMomento(CommandStatus.FAILED);
  }

  private void logMomento(CommandStatus commandStatus) {
    progressLog.add(new CommandProgress(commandStatus));
  }

  public void logMomento(Object progress) {
    if (this.commandResult.getStatus().equals(CommandStatus.INPROGRESS))
      progressLog.add(new CommandProgress(CommandStatus.INPROGRESS, progress));
  }

  public CommandResponse<Result> getCommandResult() {
    return commandResult;
  }

  public ArrayList<CommandProgress> getProgressLog() {
    return progressLog;
  }

  public String getCommandName() {
    return this.getClass().getName();
  }
}
