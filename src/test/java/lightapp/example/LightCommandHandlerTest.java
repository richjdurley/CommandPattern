package lightapp.example;

import lightapp.example.domain.Light;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommandNames;
import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandBuilder;

import java.util.LinkedList;

public class LightCommandHandlerTest {


    @Test
    public void should_switch_on_light() {
        Light light = new Light(LightState.OFF);
        LightCommandHandler lightCommandHandler = new LightCommandHandler(light);
        lightCommandHandler.handle(CommandBuilder.<String>builder().withCommandName(LightCommandNames.TURN_ON_COMMAND_NAME).build());
        Assert.assertTrue(light.isOn());
    }

    @Test
    public void should_switch_off_light() {
        Light light = new Light(LightState.ON);
        LightCommandHandler lightCommandHandler = new LightCommandHandler(light);
        lightCommandHandler.handle(CommandBuilder.<String>builder().withCommandName(LightCommandNames.TURN_OFF_COMMAND_NAME).build());
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
