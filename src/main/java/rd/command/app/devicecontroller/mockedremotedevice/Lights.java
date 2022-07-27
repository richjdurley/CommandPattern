package rd.command.app.devicecontroller.mockedremotedevice;

import org.springframework.boot.context.properties.ConstructorBinding;
import rd.command.app.devicecontroller.domain.Light;
import rd.command.app.devicecontroller.domain.LightState;

import java.util.concurrent.ConcurrentHashMap;

public class Lights {

    private ConcurrentHashMap<String, Light> devicesMap;

    @ConstructorBinding
    public Lights(String[][] devices) {
        devicesMap = new ConcurrentHashMap<>();
        for (String[] device : devices) {
            devicesMap.put(device[0], new Light(device[0], LightState.valueOf(device[1])));
        }
    }

    public Light getLightById(String deviceid) {
        return devicesMap.get(deviceid);
    }
}
