package rd.command.components.api.framework.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.core.codec.CodecException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

public class ThrowableTransformer {

    private final HttpStatus httpStatus;
    private final String message;

    private ThrowableTransformer(final Throwable throwable) {
        this.httpStatus = getStatus(throwable);
        this.message = throwable.getMessage();
    }

    public static ThrowableTransformer from(final Throwable throwable) {
        return new ThrowableTransformer(throwable);
    }

    public static <T extends Throwable> Mono<ThrowableTransformer> transform(final Mono<T> throwable) {
        return throwable.flatMap(error -> Mono.just(new ThrowableTransformer(error)));
    }

    private HttpStatus getStatus(final Throwable error) {
        if (error instanceof BadRequestException || error instanceof InvalidDefinitionException || error instanceof CodecException) {
            return HttpStatus.BAD_REQUEST;
        } else if (error instanceof PathNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
