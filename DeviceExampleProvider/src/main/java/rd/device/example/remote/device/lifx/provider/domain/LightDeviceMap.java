package rd.device.example.remote.device.lifx.provider.domain;

import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.concurrent.ConcurrentHashMap;

public class LightDeviceMap {

    private ConcurrentHashMap<String, Light> devicesMap;

    @ConstructorBinding
    public LightDeviceMap(String[][] devices) {
        devicesMap = new ConcurrentHashMap<>();
        for (String[] device : devices) {
            devicesMap.put(device[0], new Light(device[0], LightState.valueOf(device[1])));
        }
    }

    public Light getLightById(String deviceid) {
        return devicesMap.get(deviceid);
    }
}
