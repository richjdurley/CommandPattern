package rd.command.components.api.framework.handlers.ifvalidrequestthen;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ThenFunction {
    Mono<ServerResponse> then();
}
