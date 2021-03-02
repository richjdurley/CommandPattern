package rd.command.framework;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

import java.util.concurrent.Callable;

public interface CommandHandler<P, Result> {
    Callable<Result> handle(Command<P> command);
}
