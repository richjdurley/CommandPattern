package lightapp.example.api;

import rd.command.framework.domain.CommandRequest;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommand;
import lightapp.example.service.LightCommandProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rd.command.framework.domain.CommandResponse;

import java.util.concurrent.Callable;

@RestController
public class LightAppController {

  @Autowired LightCommandProcessor lightCommandProcessorService;

  @RequestMapping(value = "/light", method = RequestMethod.POST)
  public @ResponseBody Callable<CommandResponse<LightState>> lightCommand(
      @RequestBody CommandRequest commandRequest) {
    return () ->
        lightCommandProcessorService.process(
            (LightCommand) Class.forName(commandRequest.getCommandName()).newInstance());
  }
}
