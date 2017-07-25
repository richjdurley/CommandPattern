package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResponse;

public interface CommandProcessor<C extends Command<Result>, Result> {
  CommandResponse<Result> process(C command);
}
