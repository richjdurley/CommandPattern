package rd.command.framework;

import reactor.core.publisher.Mono;

public interface CommandController<P, R> {
    Mono<R> handle(String commandName, String commandActionName);

    Mono<R> handleWithPayload(String commandName, String commandActionName, P payload);
}
