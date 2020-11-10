package lightapp.example;

import lightapp.example.domain.Light;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommandNames;
import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.Command;

public class LightCommandHandlerTest {

    @Test
    public void should_switch_on_light() {
        Light light = new Light(LightState.OFF);
        LightCommandHandler lightCommandHandler = new LightCommandHandler(light);
        lightCommandHandler.handle(new Command<String>(LightCommandNames.TURN_ON_COMMAND_NAME));
        Assert.assertTrue(light.isOn());
    }

    @Test
    public void should_switch_off_light() {
        Light light = new Light(LightState.ON);
        LightCommandHandler lightCommandHandler = new LightCommandHandler(light);
        lightCommandHandler.handle(new Command<String>(LightCommandNames.TURN_OFF_COMMAND_NAME));
        Assert.assertTrue(light.isOff());
    }
}
