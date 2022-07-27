package rd.command.framework;

import rd.command.framework.domain.CommandState;
import reactor.core.publisher.Mono;

public interface CommandController<P, R> {
    Mono<CommandState<R>> handle(String commandName, String commandActionName);

    Mono<CommandState<R>> handleWithPayload(String commandName, String commandActionName, P payload);
}
