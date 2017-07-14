package lightapp.example.api;

import lightapp.example.api.request.CommandRequest;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommand;
import lightapp.example.domain.command.LightException;
import lightapp.example.service.LightCommandProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
public class LightAppController {

  @Autowired LightCommandProcessor lightCommandProcessorService;

  @RequestMapping(value = "/light", method = RequestMethod.POST)
  public @ResponseBody Callable<LightState> lightCommand(@RequestBody CommandRequest command) {
    return () ->
        lightCommandProcessorService.process(
            (LightCommand) Class.forName(command.getCommandName()).newInstance());
  }

  @ExceptionHandler
  @ResponseBody
  public String handleLightException(LightException ex) {
    return "Handled exception: " + ex.getMessage();
  }

  @ExceptionHandler
  @ResponseBody
  public String handleNotFoundException(ClassNotFoundException ex) {
    return "Unknown command: " + ex.getMessage();
  }
}
