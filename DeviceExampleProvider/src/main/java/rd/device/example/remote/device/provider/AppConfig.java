package rd.device.example.remote.device.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rd.device.example.remote.device.provider.domain.LightDeviceMap;


@Configuration
public class AppConfig {
    private String[][] test_lights = {
            {"living-room", "ON"},
            {"bed-room", "OFF"},
            {"kitchen", "ON"}
    };

    @Bean()
    public LightDeviceMap getLights() {
        return new LightDeviceMap(test_lights);
    }

 }
