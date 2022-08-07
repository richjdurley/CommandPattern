package rd.device.framework.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import rd.device.framework.CommandAdaptor;
import rd.device.framework.api.controller.handler.ifvalidrequestthen.AbstractIfValidRequestThenHandler;
import rd.device.framework.domain.CommandRequest;
import rd.device.framework.domain.CommandState;
import rd.device.framework.api.controller.handler.ErrorHandler;
import rd.device.framework.api.exceptions.EmptyRequestException;
import reactor.core.publisher.Mono;
import static java.lang.String.format;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public abstract class AbstractDeviceController<R> extends AbstractIfValidRequestThenHandler<CommandRequest> {

    @Autowired
    CommandAdaptor<R> commandAdaptor;

    @Autowired
    ErrorHandler errorHandler;

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
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
