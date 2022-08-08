package rd.device.framework.api.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import rd.device.framework.CommandAdaptor;
import rd.device.framework.api.handler.ifvalidrequestthen.AbstractIfValidRequestThenHandler;
import rd.device.framework.domain.CommandState;
import rd.device.framework.api.exceptions.EmptyRequestException;
import reactor.core.publisher.Mono;
import static java.lang.String.format;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public class BaseDeviceHandler<R> extends AbstractIfValidRequestThenHandler<CommandRequest> {

    @Autowired
    CommandAdaptor<R> commandAdaptor;

    @Autowired
    ErrorHandler errorHandler;

    @Override
    public Mono<ServerResponse> handleCommand(ServerRequest request) {
        return request.bodyToMono(CommandRequest.class).flatMap(i -> ifValidRequest(i).then(() -> formatResponse(commandAdaptor.execute(i.getCommand()))).onErrorResume(
                errorHandler::handleError).switchIfEmpty(errorHandler.handleError(new EmptyRequestException())));
    }

    public Mono<ServerResponse> handleStatus(ServerRequest request) {
        return ServerResponse.status(200).build();
    }

    private Mono<ServerResponse> formatResponse(Mono<CommandState<R>> result) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .header("Cache-Control", format("max-age=%s", 0))
                .body(result, CommandState.class);
    }

}
