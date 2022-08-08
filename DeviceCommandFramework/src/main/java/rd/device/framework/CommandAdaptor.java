package rd.device.framework;

import rd.device.framework.domain.Command;
import rd.device.framework.domain.CommandState;
import reactor.core.publisher.Mono;

public class CommandAdaptor<R> {

    private RemoteExecutionBinding<R> executionStrategy;

    public CommandAdaptor(RemoteExecutionBinding<R> executionStrategy) {
        this.executionStrategy = executionStrategy;
    }

    public Mono<CommandState<R>> execute(Command command) {
        return executionStrategy.execute(command);
    }
}
