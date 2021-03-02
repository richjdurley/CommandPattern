package rd.command.framework;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskCommandHandler<P, Result> {

    /*
    ExecutorService executor;

    public FutureTaskCommandHandler() {
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public FutureTaskCommandHandler(ExecutorService executorService) {
        this.executor = executorService;
    }

    public Future<CommandResult<Result>> accept(
            Command<P> command, CommandHandler<P, Result> commandProcessor) {
        FutureTask<CommandResult<Result>> task =
                new FutureTask<>(() -> commandProcessor.handle(command));
        executor.submit(task);
        return task;
    }
    */

}
