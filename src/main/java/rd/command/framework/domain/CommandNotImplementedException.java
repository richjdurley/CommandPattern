package rd.command.framework.domain;

public class CommandNotImplementedException extends CommandException {
  Command command;

  public CommandNotImplementedException(Command command) {
    this.command = command;
  }
}
