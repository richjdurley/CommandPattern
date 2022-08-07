package rd.device.framework.api.controller.handler.ifvalidrequestthen;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ThenFunction {
    Mono<ServerResponse> then();
}
