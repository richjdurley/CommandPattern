package lightapp.example;

import lightapp.example.domain.Light;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommandNames;
import lightapp.example.domain.command.LightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rd.command.framework.CommandHandler;
import rd.command.framework.domain.Command;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LightCommandHandler implements CommandHandler<Object, LightState> {

    private java.util.logging.Logger log =
            Logger.getLogger(LightCommandHandler.class.getSimpleName());

    @Autowired
    LightAdaptor lightAdaptor;

    public LightCommandHandler(LightAdaptor lightAdaptor) {
        this.lightAdaptor = lightAdaptor;
    }

    public Callable<LightState> handle(Command<Object> command) {
        boolean valid=false;
        try {
            if (command.getCommandActionName().equals(LightCommandNames.TURN_ON_COMMAND_NAME)) {
                log.info("Valid command to switch ON the light");
            }

            if (command.getCommandActionName().equals(LightCommandNames.TURN_OFF_COMMAND_NAME)) {
                log.info("Valid command to switch OFF the light");
            }

            return lightAdaptor.action(command);


        } catch (LightException e) {
            log.log(Level.WARNING, "exception occurred --> ", e);
            throw e;
        }
    }
}
