package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandException;
import rd.command.framework.domain.CommandResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public abstract class FutureTaskCommandProcessor<C extends Command<Result>, Result> extends CommandProcessor {

  ExecutorService executor = Executors.newFixedThreadPool(4);

  public final FutureTask<C> accept(final C command) {
    FutureTask<C> task =
        new FutureTask<C>(
            new Callable<C>() {
              public C call() throws Exception {
                try {
                  command.commandSucceeded((process(command)));
                } catch (CommandException e) {
                  command.commandFailed(e);
                }
                return command;
              }
            });
    executor.execute(task);
    return task;
  }

  public abstract Result process(Command command) throws CommandException;

  public void close() {
    executor.shutdownNow();
  }
}
