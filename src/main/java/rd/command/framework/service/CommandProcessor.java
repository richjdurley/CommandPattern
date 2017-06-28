package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandException;

abstract class CommandProcessor<C extends Command<Result>, Result> {
  public abstract Result process(C command) throws CommandException;
}
