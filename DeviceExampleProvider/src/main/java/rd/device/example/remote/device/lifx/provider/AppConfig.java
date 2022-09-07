package rd.device.example.remote.device.lifx.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rd.device.framework.api.handler.ErrorHandler;


@Configuration
public class AppConfig {
    @Bean
    public ErrorHandler getErrorHandler() {
        return new ErrorHandler();
    }

}
