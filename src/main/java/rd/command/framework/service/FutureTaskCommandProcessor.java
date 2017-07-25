package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public abstract class FutureTaskCommandProcessor<C extends Command<Result>, Result>
    implements FutureCommandProcessor<C, Result> {

  ExecutorService executor;

  public FutureTaskCommandProcessor() {
    this.executor = Executors.newFixedThreadPool(4);
  }

  public FutureTaskCommandProcessor(ExecutorService executorService) {
    this.executor = executorService;
  }

  public Future<CommandResponse<Result>> accept(C command) {
    FutureTask<CommandResponse<Result>> task = new FutureTask<>(() -> process(command));
    executor.execute(task);
    return task;
  }

  public void close() {
    executor.shutdownNow();
  }
}
