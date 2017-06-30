package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandException;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public abstract class FutureTaskCommandProcessor<C extends Command<Result>, Result>
    implements CommandProcessor<C, Result> {

  ExecutorService executor = Executors.newFixedThreadPool(4);

  @Override
  public FutureTask<C> accept(C command) {
    FutureTask<C> task =
        new FutureTask<C>(() -> {
          try {
            command.commandSucceeded((process(command)));
          } catch (CommandException e) {
            command.commandFailed(e);
          }
          return command;
        });
    executor.execute(task);
    return task;
  }

  public abstract Result process(C command) throws CommandException;

  public void close() {
    executor.shutdownNow();
  }
}
