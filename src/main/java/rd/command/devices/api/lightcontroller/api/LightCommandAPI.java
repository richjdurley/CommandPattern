package rd.command.devices.api.lightcontroller.api;

import rd.command.devices.api.lightcontroller.api.handlers.LightCommandHandler;
import rd.command.devices.api.lightcontroller.domain.LightState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rd.command.framework.CommandController;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandBuilder;

import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "/device")
public class LightCommandAPI implements CommandController<Object, LightState> {

    @Autowired
    LightCommandHandler lightCommandHandler;

    @Override
    @RequestMapping(value = "/{devicename}/{lightcommandname}", method = RequestMethod.POST)
    public Callable<LightState> handle(@PathVariable String devicename, @PathVariable String lightcommandname) {
        Command<Object> command = CommandBuilder.builder().withTargetDeviceName(devicename).withCommandActionName(lightcommandname).build();
        return lightCommandHandler.handle(command);
    }

    @Override
    public Callable<LightState> handleWithPayload(String commandName, String commandActionName, Object payload) {
        return null;
    }
}
