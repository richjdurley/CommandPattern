package rd.command.framework;

import org.springframework.stereotype.Component;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandState;
import reactor.core.publisher.Mono;

public class CommandAdaptor<P, R> {

    private ExecutionStrategy<P,R> executionStrategy;

    public CommandAdaptor(ExecutionStrategy<P,R> executionStrategy) {
        this.executionStrategy = executionStrategy;
    }

    public Mono<CommandState<R>> execute(Command<P> command) {
        return executionStrategy.execute(command);
    }
}
