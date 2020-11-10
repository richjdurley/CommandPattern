package rd.command.framework;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

public interface CommandHandler<C extends Command, Result> {
    CommandResult<Result> handle(C command);
}
