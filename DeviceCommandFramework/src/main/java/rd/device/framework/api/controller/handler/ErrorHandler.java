package rd.device.framework.api.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import rd.device.framework.api.exceptions.ErrorResponseTransformer;
import reactor.core.publisher.Mono;

@Component
public class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    public Mono<ServerResponse> handleError(final Throwable error) {
        return Mono.just(error).transform(ErrorResponseTransformer::transformToServerResponse)
                .doOnNext(serverResponse -> logger.error("Error occurred processing", error));
    }
}
