package rd.command.framework;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandState;
import reactor.core.publisher.Mono;

public interface ExecutionStrategy<P,R> {
    Mono<CommandState<R>> execute(Command<P> command);
}
