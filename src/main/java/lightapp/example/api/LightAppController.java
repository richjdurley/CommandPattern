package lightapp.example.api;

import lightapp.example.LightCommandHandler;
import lightapp.example.domain.LightState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

import java.util.concurrent.Callable;

@RestController
public class LightAppController {

    @Autowired
    LightCommandHandler lightCommandHandler;

    @RequestMapping(value = "/light", method = RequestMethod.POST)
    public @ResponseBody
    Callable<CommandResult<LightState>> lightCommand(
            @RequestBody final Command lightCommand) {
        return () ->
                lightCommandHandler.handle(lightCommand);
    }
}
