package lightapp.example;

import lightapp.example.domain.LightState;
import lightapp.example.domain.command.TurnLightOffCommand;
import lightapp.example.domain.command.TurnLightOnCommand;
import lightapp.example.service.LightCommandProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rd.command.framework.domain.CommandException;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/light")
public class LightAppController  {

  @Autowired LightCommandProcessor lightCommandProcessorService;

  @RequestMapping(value = "/on", method = RequestMethod.POST)
  public @ResponseBody Callable<LightState> lightOn() {
    return () -> lightCommandProcessorService.process(new TurnLightOnCommand());
  }

  @RequestMapping(value = "/off", method = RequestMethod.POST)
  public @ResponseBody Callable<LightState> lightOff() {
    return () -> lightCommandProcessorService.process(new TurnLightOffCommand());
  }

  @ExceptionHandler
  @ResponseBody
  public String handleException(CommandException ex) {
    return "Handled exception: " + ex.getMessage();
  }
}
