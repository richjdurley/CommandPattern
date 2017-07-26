package lightapp.example.service;

import lightapp.example.domain.Light;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommand;
import lightapp.example.domain.command.LightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rd.command.framework.domain.CommandNotImplementedException;
import rd.command.framework.domain.CommandResult;
import rd.command.framework.domain.FailedResult;
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
      if (command.getCommandName().equals(LightCommand.TURN_ON_COMMAND_NAME)) {
        synchronized (lock) {
          log.info("Started to switch ON the light");
          LockSupport.parkNanos(1000 * 1000 * 1000);
          light.turnOnLight();
          return new CommandResult<>(light.getLightState());
        }
      }

      if (command.getCommandName().equals(LightCommand.TURN_OFF_COMMAND_NAME)) {
        synchronized (lock) {
          log.info("Started to switch OFF the light");
          LockSupport.parkNanos(1000 * 1000 * 1000);
          light.turnOffLight();
          return new CommandResult<>(light.getLightState());
        }
      }

      throw new CommandNotImplementedException(command);

    } catch (LightException e) {
      log.log(Level.WARNING, "exception occurred --> ", e);
      return new CommandResult<>(new FailedResult(e.getMessage()));
    }
  }
}
