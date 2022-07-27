package rd.command.app.devicecontroller.adaptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import rd.command.app.devicecontroller.model.LightDeviceResult;
import rd.command.framework.ExecutionStrategy;
import rd.command.framework.api.exceptions.BadRequestException;
import rd.command.framework.api.exceptions.InfrastructureException;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandState;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

public class LightCommandExecutor<O, L> implements ExecutionStrategy<Object,LightDeviceResult> {

    private java.util.logging.Logger log =
            Logger.getLogger(LightCommandExecutor.class.getSimpleName());

    @Override
    public Mono<CommandState<LightDeviceResult>> execute(Command<Object> command) {
        return WebClient.create().post()
                .uri(builder -> builder.scheme("http")
                        .host("localhost").port(8080).path("/mock/provider/" + command.getTargetResourceURI() + "/" + command.getName().substring(command.getName().lastIndexOf('_') + 1))
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new InfrastructureException("Internal Server Error")))
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new BadRequestException("Bad Request"))).
                bodyToMono(LightDeviceResult.class).map(ldr-> new CommandState<>(new LightDeviceResult(ldr.getDeviceresult())));
    }

}
