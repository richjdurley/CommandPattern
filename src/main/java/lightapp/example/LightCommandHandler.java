package lightapp.example;

import lightapp.example.domain.Light;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommandNames;
import lightapp.example.domain.command.LightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rd.command.framework.CommandHandler;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandNotImplementedException;
import rd.command.framework.domain.CommandResult;
import rd.command.framework.domain.FailedResult;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LightCommandHandler implements CommandHandler<Command, LightState> {

    @Autowired
    private Light light;

    private java.util.logging.Logger log =
            Logger.getLogger(LightCommandHandler.class.getSimpleName());

    public LightCommandHandler(Light light) {
        this.light = light;
    }

    @Override
    public CommandResult<LightState> handle(Command command) {
        try {
            if (command.getCommandName().equals(LightCommandNames.TURN_ON_COMMAND_NAME)) {
                log.info("Started to switch ON the light");
                light.turnOnLight();
                return new CommandResult<>(light.getLightState());
            }

            if (command.getCommandName().equals(LightCommandNames.TURN_OFF_COMMAND_NAME)) {
                log.info("Started to switch OFF the light");
                light.turnOffLight();
                return new CommandResult<>(light.getLightState());
            }

            throw new CommandNotImplementedException(command);

        } catch (LightException e) {
            log.log(Level.WARNING, "exception occurred --> ", e);
            return new CommandResult<>(new FailedResult(e.getMessage()));
        }
    }
}
