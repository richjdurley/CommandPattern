package rd.command.framework;

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
