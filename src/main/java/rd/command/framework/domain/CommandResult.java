package rd.command.framework.domain;

import java.util.ArrayList;

public class CommandResult<R> {
  CommandStatus status;
  R result;
  CommandFailedResult failedResult;


  public CommandResult() {}

  public CommandResult(R result) {
    this.status = CommandStatus.SUCCEEDED;
    this.result = result;
  }

  public CommandResult(CommandFailedResult failedResult) {
    this.status = CommandStatus.FAILED;
    this.failedResult = failedResult;
  }

  public CommandStatus getStatus() {
    return status;
  }

  public R getResult() {
    return result;
  }

  public CommandFailedResult getFailedResult() {
    return failedResult;
  }

  public void setResult(R result) {
    this.result = result;
  }

  public void setStatus(CommandStatus status) {
    this.status = status;
  }

  public void setFailedResult(CommandFailedResult failedResult) {
    this.failedResult = failedResult;
  }
}
