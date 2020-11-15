package rd.command.framework;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

public interface CommandHandler<P, Result> {
    CommandResult<Result> handle(Command<P> command);
}
