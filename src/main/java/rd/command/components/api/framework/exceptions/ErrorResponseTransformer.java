package rd.command.components.api.framework.exceptions;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ErrorResponseTransformer {
    public static <T extends Throwable> Mono<ServerResponse> transformToServerResponse(final Mono<T> monoError) {
        return monoError.transform(ThrowableTransformer::transform)
                .flatMap(translation -> ServerResponse
                        .status(translation.getHttpStatus()).contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(new ErrorResponse(translation.getHttpStatus(), translation.getMessage())), ErrorResponse.class));
    }

    public static <T extends Throwable> ErrorResponse transformToErrorResponse(final Throwable error) {
        return new ErrorResponse(ThrowableTransformer.from(error).getHttpStatus(), ThrowableTransformer.from(error).getMessage());
    }

}
