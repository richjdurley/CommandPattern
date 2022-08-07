package rd.device.framework.api.controller.handler.ifvalidrequestthen;


import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface IfValidRequestThen<T> {
    IfValidRequestThen<T> ifValidRequest(T request);

    Mono<ServerResponse> then(ThenFunction thenFunction);
}
