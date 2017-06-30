package lightapp.example.service;

import lightapp.example.domain.Light;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommand;
import lightapp.example.domain.command.LightException;
import lightapp.example.domain.command.TurnLightOffCommand;
import lightapp.example.domain.command.TurnLightOnCommand;
import rd.command.framework.domain.CommandException;
import rd.command.framework.domain.CommandNotImplementedException;
import rd.command.framework.service.FutureTaskCommandProcessor;

public class LightCommandProcessorService
    extends FutureTaskCommandProcessor<LightCommand, LightState> {

  private Light light;
  private Object lock = new Object();

  public LightCommandProcessorService(Light light) {
    this.light = light;
  }

  @Override
  public LightState process(LightCommand command) throws CommandException {
    try {
      if (command instanceof TurnLightOnCommand) {
        synchronized (lock) {
          light.turnOnLight();
          System.out.println("event --> Light was switched on");
          return light.getLightState();
        }
      }

      if (command instanceof TurnLightOffCommand) {
        synchronized (lock) {
          light.turnOffLight();
          System.out.println("event --> Light was switched off");
          return light.getLightState();
        }
      }
    } catch (LightException e) {
      throw new CommandException(e);
    }
    throw new CommandNotImplementedException(command);
  }
}
