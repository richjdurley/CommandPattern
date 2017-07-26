package rd.command.framework.service;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskCommandProcessor<C extends Command<Result>, Result> {

  ExecutorService executor;

  public FutureTaskCommandProcessor() {
    this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }

  public FutureTaskCommandProcessor(ExecutorService executorService) {
    this.executor = executorService;
  }

  public Future<CommandResult<Result>> accept(
      C command, CommandProcessor<C, Result> commandProcessor) {
    FutureTask<CommandResult<Result>> task =
        new FutureTask<>(() -> commandProcessor.process(command));
    executor.submit(task);
    return task;
  }
}
