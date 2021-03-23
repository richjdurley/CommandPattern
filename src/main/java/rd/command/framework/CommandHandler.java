package rd.command.framework;

import rd.command.framework.domain.Command;
import reactor.core.publisher.Mono;

public interface CommandHandler<Payload, Result> {
    Mono<Result> handle(Command<Payload> command);
}
