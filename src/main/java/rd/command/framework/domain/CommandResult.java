package rd.command.framework.domain;

public class CommandResult<R> {
  CommandStatus status;
  R result;
  CommandException exception;

  public CommandStatus getStatus() {
    return status;
  }

  public R getResult() {
    return result;
  }

  public CommandException getException() {
    return exception;
  }

  public void setResult(R result) {
    this.result = result;
  }

  public void setStatus(CommandStatus status) {
    this.status = status;
  }

  public void setException(CommandException exception) {
    this.exception = exception;
  }
}
