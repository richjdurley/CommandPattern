package lightapp.example.domain.command;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;
import lightapp.example.domain.LightState;

public class LightCommand extends Command<LightState> {
  @Override
  public CommandResult<LightState> getCommandResult() {
    return super.getCommandResult();
  }
}
