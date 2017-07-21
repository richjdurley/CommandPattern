package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandNotImplementedException;
import rd.command.framework.domain.CommandRequest;
import rd.command.framework.domain.CommandResult;

import java.util.concurrent.Future;

public class CommandExecutor<C extends Command<Result>, Result> {

  FutureCommandProcessor<C, Result> commandProcessor;

  public CommandExecutor(FutureCommandProcessor<C, Result> commandProcessor) {
    this.commandProcessor = commandProcessor;
  }

  Future<CommandResult<Result>> execute(CommandRequest<C> commandRequest) {
    try {
      C command = (C) Class.forName(commandRequest.getCommandName()).newInstance();
      return commandProcessor.accept(command);
    } catch (Exception e) {

    }
    throw new CommandNotImplementedException(commandRequest);
  }
}
