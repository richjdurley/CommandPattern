package rd.command.app.lightcontrolexample.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rd.command.app.lightcontrolexample.model.LightDeviceResult;
import rd.command.framework.CommandController;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandBuilder;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/device")
public class LightCommandAPI implements CommandController<Object, LightDeviceResult> {

    @Autowired
    LightCommandHandler lightCommandHandler;

    @Override
    @RequestMapping(value = "/{devicename}/{lightcommandname}", method = RequestMethod.POST)
    public Mono<LightDeviceResult> handle(@PathVariable String devicename, @PathVariable String lightcommandname) {
        //handles the light command and returns the result string "success" or throws an exception
        Command<Object> command = CommandBuilder.builder().withTargetDeviceName(devicename).withCommandActionName(lightcommandname).build();
        return lightCommandHandler.handle(command);
    }

    @Override
    public Mono<LightDeviceResult> handleWithPayload(String commandName, String commandActionName, Object payload) {
        return Mono.empty();
    }
}
