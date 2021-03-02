package rd.command.framework;

import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;

import java.util.concurrent.Callable;

public interface CommandController<P,R> {
    Callable<R> handle(String commandName, String commandActionName);
    Callable<R> handleWithPayload(String commandName, String commandActionName, P payload);
}
