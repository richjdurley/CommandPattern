package lightapp.example;

import lightapp.example.domain.LightState;
import org.springframework.stereotype.Service;
import rd.command.framework.DeviceAdaptor;
import rd.command.framework.domain.Command;

import java.util.concurrent.Callable;

@Service
public class LightAdaptor implements DeviceAdaptor<Object, LightState> {
    @Override
    public Callable<LightState> action(Command<Object> command) {
        return () -> {
            // Perform some computation
            Thread.sleep(1000);
            return LightState.ON;
        };
    }
}
