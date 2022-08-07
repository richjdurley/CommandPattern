package rd.device.example.client.device.app.adaptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import rd.device.example.client.device.app.model.LightDeviceResult;
import rd.device.framework.ExecutionStrategy;
import rd.device.framework.api.exceptions.BadRequestException;
import rd.device.framework.api.exceptions.InfrastructureException;
import rd.device.framework.domain.Command;
import rd.device.framework.domain.CommandState;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

public class LightAdaptor implements ExecutionStrategy<LightDeviceResult> {

    private java.util.logging.Logger log =
            Logger.getLogger(LightAdaptor.class.getSimpleName());

    @Override
    public Mono<CommandState<LightDeviceResult>> execute(Command command) {
        return WebClient.create().post()
                .uri(builder -> builder.scheme("http")
                        .host("localhost").port(8080).path("/mock/provider/" + command.getDeviceResourceURI() + "/" + command.getName().substring(command.getName().lastIndexOf('_') + 1))
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new InfrastructureException("Internal Server Error")))
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new BadRequestException("Bad Request"))).
                bodyToMono(LightDeviceResult.class).map(ldr-> new CommandState<>(new LightDeviceResult(ldr.getDeviceresult())));
    }

}
