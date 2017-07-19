package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandException;
import rd.command.framework.domain.CommandResult;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public interface CommandProcessor<C extends Command<Result>, Result> {
  CommandResult<Result> process(C command) throws CommandException;
}
