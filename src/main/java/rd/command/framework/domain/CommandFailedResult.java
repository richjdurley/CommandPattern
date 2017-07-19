package rd.command.framework.domain;

public class CommandFailedResult {

  String message;
  Throwable cause;

  public CommandFailedResult() {
    this.message = "Command failedResult occurred";
  }

  public CommandFailedResult(String message) {
    this.message = message;
  }

  public CommandFailedResult(String message, Throwable cause) {
    this.message=message;
    this.cause = cause;
  }

  public String getMessage() {
    return message;
  }

  public Throwable getCause() {
    return cause;
  }
}
