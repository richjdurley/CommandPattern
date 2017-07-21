package rd.command.framework.domain;

public class CommandNotImplementedException extends RuntimeException {
  CommandRequest commandRequest;

  public CommandNotImplementedException(CommandRequest commandRequest) {
    this.commandRequest = commandRequest;
  }
}
