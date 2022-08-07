package rd.device.example.client.device.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rd.device.example.client.device.app.adaptor.LightAdaptor;
import rd.device.example.client.device.app.model.LightDeviceResult;
import rd.device.framework.api.controller.handler.ErrorHandler;
import rd.device.framework.CommandAdaptor;

@Configuration
public class AppInfo {
    @Bean
    public ErrorHandler getErrorHandler() {
        return new ErrorHandler();
    }

    @Bean
    public CommandAdaptor<LightDeviceResult> getCommandAdaptor() { return new CommandAdaptor<>(new LightAdaptor());}

}
