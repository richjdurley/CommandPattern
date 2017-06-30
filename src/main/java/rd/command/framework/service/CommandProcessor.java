package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandException;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public interface CommandProcessor<C extends Command<Result>, Result> {
  Result process(C command) throws CommandException;
  Future<C> accept(C command);
}
