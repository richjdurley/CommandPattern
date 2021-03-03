package rd.command.framework;

import org.springframework.util.concurrent.ListenableFutureCallback;
import rd.command.framework.domain.Command;

import java.util.concurrent.Callable;

public interface DeviceAdaptor<P,R> {
    public Callable<R> action(Command<P> command);
}
