package rd.command.framework;

import rd.command.framework.domain.Command;
import reactor.core.publisher.Mono;

public interface Adaptor<P, R> {
    public Mono<R> action(Command<P> command);
}
