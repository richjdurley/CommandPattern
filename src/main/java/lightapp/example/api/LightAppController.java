package lightapp.example.api;

import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommand;
import lightapp.example.service.LightCommandProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rd.command.framework.domain.CommandResult;
import rd.command.framework.domain.NullCommandData;

import java.util.concurrent.Callable;

@RestController
public class LightAppController {

  @Autowired LightCommandProcessor lightCommandProcessorService;

  @RequestMapping(value = "/light", method = RequestMethod.POST)
  public @ResponseBody Callable<CommandResult<LightState>> lightCommand(
      @RequestBody LightCommand lightCommand) {
    return () ->
        lightCommandProcessorService.process(lightCommand);
  }
}
