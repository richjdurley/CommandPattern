package rd.command.framework.domain;

public class CommandResult<R> {
  CommandStatus status;
  R result;
  FailedResult failedResult;

  public CommandResult(R result) {
    this.status = CommandStatus.SUCCEEDED;
    this.result = result;
  }

  public CommandResult(FailedResult failedResult) {
    this.status = CommandStatus.FAILED;
    this.failedResult = failedResult;
  }

  public CommandStatus getStatus() {
    return status;
  }

  public R getSuccessResult() {
    return result;
  }

  public FailedResult getFailedResult() {
    return failedResult;
  }

  public void setSuccessResult(R result) {
    this.result = result;
  }

  public void setFailedResult(FailedResult failedResult) {
    this.failedResult = failedResult;
  }
}
