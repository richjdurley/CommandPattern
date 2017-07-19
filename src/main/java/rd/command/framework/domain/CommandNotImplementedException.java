package rd.command.framework.domain;

public class CommandNotImplementedException extends RuntimeException {
  Command command;

  public CommandNotImplementedException(Command command) {
    this.command = command;
  }
}
