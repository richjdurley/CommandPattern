package rd.device.example.remote.device.lifx.provider;

import com.stuntguy3000.lifxlansdk.helper.LightHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rd.device.example.remote.device.lifx.provider.domain.LightDeviceMap;
import rd.device.framework.api.handler.ErrorHandler;


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

    @Bean
    public ErrorHandler getErrorHandler() {
        return new ErrorHandler();
    }

 }
