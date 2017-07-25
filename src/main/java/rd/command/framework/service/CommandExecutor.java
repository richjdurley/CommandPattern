package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandNotImplementedException;
import rd.command.framework.domain.CommandResponse;

import java.util.concurrent.Future;

public class CommandExecutor<C extends Command<Result>, Result> {

  FutureCommandProcessor<C, Result> commandProcessor;

  public CommandExecutor(FutureCommandProcessor<C, Result> commandProcessor) {
    this.commandProcessor = commandProcessor;
  }

  public Future<CommandResponse<Result>> execute(C command) {
    try {
      return commandProcessor.accept(command);
    } catch (Exception e) {

    }
    throw new CommandNotImplementedException(command);
  }
}
