package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

public interface CommandProcessor<C extends Command, Result> {
  CommandResult<Result> process(C command);
}
