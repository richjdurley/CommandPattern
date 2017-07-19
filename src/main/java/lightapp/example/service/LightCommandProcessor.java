package lightapp.example.service;

import lightapp.example.domain.Light;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommand;
import lightapp.example.domain.command.LightException;
import lightapp.example.domain.command.TurnLightOffCommand;
import lightapp.example.domain.command.TurnLightOnCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rd.command.framework.domain.CommandResult;
import rd.command.framework.service.CommandProcessor;

import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LightCommandProcessor implements CommandProcessor<LightCommand, LightState> {

  @Autowired private Light light;
  private Object lock = new Object();

  private java.util.logging.Logger log =
      Logger.getLogger(LightCommandProcessor.class.getSimpleName());

  @Override
  public CommandResult<LightState> process(LightCommand command) {
    try {
      if (command instanceof TurnLightOnCommand) {
        synchronized (lock) {
          command.logProgress("Started to switch ON the light");
          LockSupport.parkNanos(1000 * 1000 * 1000);
          light.turnOnLight();
          command.commandSucceeded(light.getLightState());
          log.info("Command " + command.getCommandID() + " --> Switched ON the light");
        }
      }

      if (command instanceof TurnLightOffCommand) {
        synchronized (lock) {
          command.logProgress("Started to switch OFF the light");
          LockSupport.parkNanos(1000 * 1000 * 1000);
          light.turnOffLight();
          log.info("Command " + command.getCommandID() + " --> Switched OFF the light");
          command.commandSucceeded(light.getLightState());
        }
      }

    } catch (LightException e) {
      log.log(Level.WARNING, "exception occurred --> ", e);
      command.commandFailed(e.getMessage());
    }
    return command.getCommandResult();
  }
}
