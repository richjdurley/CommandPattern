import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandBuilder;

import static org.hamcrest.CoreMatchers.is;

public class CommandTestShould {

    public static final String NEW_COMMAND = "NEW_COMMAND";
    public static final String COMMAND_DATA = "COMMAND DATA";

    @Test
    public void haveUUIDAndTimeStampOnNewCommand() {
        Command testCommand = CommandBuilder.builder().build();
        Assert.assertNotNull(testCommand.getCommandUUID());
        Assert.assertTrue(testCommand.getCommandTimestamp()>0);
    }

    @Test
    public void haveACommandWithNewCommandName() {
        Command testCommand = CommandBuilder.builder().withCommandName(NEW_COMMAND).build();
        Assert.assertThat(testCommand.getCommandName(), is(NEW_COMMAND));
    }

    @Test
    public void haveACommandWithData() {
        Command<String> testCommand = CommandBuilder.<String>builder().withCommandPayload(COMMAND_DATA).build();
        Assert.assertThat(testCommand.getCommandPayload(), is(COMMAND_DATA));
    }

}
