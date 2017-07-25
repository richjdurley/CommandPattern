package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResponse;

import java.util.concurrent.Future;

public interface FutureCommandProcessor<C extends Command<Result>, Result>
    extends CommandProcessor<C, Result> {
  Future<CommandResponse<Result>> accept(C command);
}
