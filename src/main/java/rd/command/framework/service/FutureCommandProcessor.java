package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

import java.util.concurrent.Future;

public interface FutureCommandProcessor<C extends Command<Result>, Result>
    extends CommandProcessor<C, Result> {
  Future<CommandResult<Result>> accept(C command);
}
