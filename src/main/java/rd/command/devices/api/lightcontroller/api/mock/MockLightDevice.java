package rd.command.devices.api.lightcontroller.api.mock;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rd.command.devices.api.lightcontroller.domain.LightState;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
public class MockLightDevice {

    private ConcurrentHashMap<String, AtomicBoolean> lightStates = new ConcurrentHashMap<>();

    //turn on
    @RequestMapping(value = "/{devicename}/TURN_ON", method = RequestMethod.POST)
    public void handle(@PathVariable String devicename, @PathVariable String requiredlightstate) {
        lightStates.get(devicename).getAndSet(true);
    }


    //turn off

}
