package rd.command.app.devicecontroller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rd.command.app.devicecontroller.adaptor.LightCommandExecutor;
import rd.command.app.devicecontroller.domain.LightState;
import rd.command.app.devicecontroller.mockedremotedevice.Lights;
import rd.command.app.devicecontroller.model.LightDeviceResult;
import rd.command.framework.CommandAdaptor;
import rd.command.framework.api.handlers.ErrorHandler;

@Configuration
public class AppConfig {
    private String[][] test_lights = {
            {"living-room", "ON"},
            {"bed-room", "OFF"},
            {"kitchen", "ON"}
    };

    @Bean()
    public Lights getLights() {
        return new Lights(test_lights);
    }

    @Bean
    public ErrorHandler getErrorHandler() {
        return new ErrorHandler();
    }

    @Bean
    public CommandAdaptor getCommandAdaptor() { return new CommandAdaptor<Object, LightState>(new LightCommandExecutor());}
}
