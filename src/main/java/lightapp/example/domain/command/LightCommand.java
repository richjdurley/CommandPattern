package lightapp.example.domain.command;

import lightapp.example.domain.LightState;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

public abstract class LightCommand extends Command<LightState> {

  @Override
  public CommandResult<LightState> getCommandResult() {
    return super.getCommandResult();
  }
}
