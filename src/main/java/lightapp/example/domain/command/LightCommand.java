package lightapp.example.domain.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.NullCommandData;

public class LightCommand extends Command<NullCommandData> {
  public static final String TURN_ON_COMMAND_NAME = "TURN_ON";
  public static final String TURN_OFF_COMMAND_NAME = "TURN_OFF";

  public LightCommand() {}

  @JsonCreator
  public LightCommand(String commandName) {
    super(commandName);
  }
}
