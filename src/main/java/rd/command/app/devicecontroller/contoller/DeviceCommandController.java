package rd.command.app.devicecontroller.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import rd.command.framework.CommandAdaptor;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandBuilder;
import rd.command.framework.domain.CommandState;
import reactor.core.publisher.Mono;
import static java.lang.String.format;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
@RequestMapping(value = "/device")
public class DeviceCommandController<P, R> {

    @Autowired
    CommandAdaptor<P, R> commandAdaptor;

    @RequestMapping(value = "/{devicename}/{commandname}", method = RequestMethod.POST)
    public Mono<ServerResponse> handle(@PathVariable String devicename, @PathVariable String commandname) {
        //handles the command and returns the result string "success" or throws an exception
        //todo add payload
        Command<P> command = CommandBuilder.builder().withTargetResourceURI(devicename).withCommandName(commandname).build();
        return commandAdaptor.execute(command).flatMap(this::formatResponse);
    }

    private Mono<ServerResponse> formatResponse(CommandState<R> result) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .header("Cache-Control", format("max-age=%s", 0))
                .body(Mono.just(result), CommandState.class);
    }

}
