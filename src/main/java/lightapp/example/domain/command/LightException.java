package lightapp.example.domain.command;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandException;

public class LightException extends Exception {
  public LightException(String message) {
    super(message);
  }
}
