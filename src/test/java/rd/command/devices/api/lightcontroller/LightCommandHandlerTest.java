package rd.command.devices.api.lightcontroller;

import rd.command.devices.api.lightcontroller.adaptor.LightAdaptor;
import rd.command.devices.api.lightcontroller.api.handlers.LightCommandHandler;
import rd.command.devices.api.lightcontroller.domain.Light;
import rd.command.devices.api.lightcontroller.domain.LightState;
import rd.command.devices.api.lightcontroller.domain.command.LightCommandNames;
import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.CommandBuilder;

public class LightCommandHandlerTest {


    @Test
    public void should_switch_on_light() {
        Light light = new Light(LightState.OFF);
        LightAdaptor lightAdaptor = new LightAdaptor();
        LightCommandHandler lightCommandHandler = new LightCommandHandler(lightAdaptor);
        lightCommandHandler.handle(CommandBuilder.builder().withTargetDeviceName(LightCommandNames.TURN_ON_COMMAND_NAME).build());
        Assert.assertTrue(light.isOn());
    }

    @Test
    public void should_switch_off_light() {
        Light light = new Light(LightState.ON);
        LightAdaptor lightAdaptor = new LightAdaptor();
        LightCommandHandler lightCommandHandler = new LightCommandHandler(lightAdaptor);
        lightCommandHandler.handle(CommandBuilder.builder().withCommandActionName(LightCommandNames.TURN_OFF_COMMAND_NAME).build());
        Assert.assertTrue(light.isOff());
    }

    /*
    @Test
    public void should_be_composable() {
        Light light = new Light(LightState.ON);
        LightCommandHandler lightCommandHandler = new LightCommandHandler(light);
        LinkedList<Command<String>> commandList = new LinkedList<>();
        commandList.add(CommandBuilder.<String>builder().withCommandName(LightCommandNames.TURN_OFF_COMMAND_NAME).build());
        commandList.add(CommandBuilder.<String>builder().withCommandName(LightCommandNames.TURN_ON_COMMAND_NAME).build());
        lightCommandHandler.handle(commandList);
        Assert.assertTrue(light.isOn());
    }*/

}
