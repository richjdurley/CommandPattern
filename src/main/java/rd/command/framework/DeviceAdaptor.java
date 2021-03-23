package rd.command.framework;

import rd.command.framework.domain.Command;

import java.util.concurrent.Future;

public interface DeviceAdaptor<P, R> {
    public Future<R> action(Command<P> command);
}
