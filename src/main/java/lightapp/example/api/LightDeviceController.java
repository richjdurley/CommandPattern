package lightapp.example.api;

import lightapp.example.LightCommandHandler;
import lightapp.example.domain.LightState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rd.command.framework.CommandController;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandBuilder;

import java.util.concurrent.Callable;

@RestController
public class LightDeviceController implements CommandController<Object, LightState> {

    @Autowired
    LightCommandHandler lightCommandHandler;

    @Override
    @RequestMapping(value = "/{lightname}/{lightcommandname}", method = RequestMethod.POST)
    public Callable<LightState> handle(String deviceName, String commandActionName) {
        Command<Object> command = CommandBuilder.builder().withTargetDeviceName(deviceName).withCommandActionName(commandActionName).build();
        return lightCommandHandler.handle(command);
    }

    @Override
    public Callable<LightState> handleWithPayload(String commandName, String commandActionName, Object payload) {
        return null;
    }
}
