package rd.command.framework.api.handlers.ifvalidrequestthen;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public abstract class AbstractIfValidRequestThenHandler<T extends ValidatableRequest> implements IfValidRequestThen<T> {

    @Override
    public IfValidRequestThen<T> ifValidRequest(T request) {
        request.validate();
        return this;
    }

    @Override
    public Mono<ServerResponse> then(ThenFunction thenFunction) {
        return thenFunction.then();
    }

    public abstract Mono<ServerResponse> handle(ServerRequest request);

}
