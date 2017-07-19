package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public abstract class FutureTaskCommandProcessor<C extends Command<Result>, Result>
    implements CommandProcessor<C, Result> {

  ExecutorService executor = Executors.newFixedThreadPool(4);

  public FutureTask<CommandResult<Result>> accept(C command) {
    FutureTask<CommandResult<Result>> task = new FutureTask<>(() -> process(command));
    executor.execute(task);
    return task;
  }

  public void close() {
    executor.shutdownNow();
  }
}
