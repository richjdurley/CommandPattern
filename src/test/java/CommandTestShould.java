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
        Assert.assertTrue(testCommand.getCommandTimestamp() > 0);
    }

    @Test
    public void haveACommandWithNewCommandName() {
        Command testCommand = CommandBuilder.builder().withTargetDeviceName(NEW_COMMAND).build();
        Assert.assertThat(testCommand.getCommandActionName(), is(NEW_COMMAND));
    }

    @Test
    public void haveACommandWithData() {
        Command<String> testCommand = CommandBuilder.<String>builder().withCommandPayload(COMMAND_DATA).build();
        Assert.assertThat(testCommand.getCommandPayload(), is(COMMAND_DATA));
    }

    @Test
    public void byDefaultExpiresAfter1000Milliseconds() throws InterruptedException {
        Command testExpiredCommand = CommandBuilder.builder().withTargetDeviceName(NEW_COMMAND).build();
        Thread.sleep(1000);
        Assert.assertTrue(!testExpiredCommand.isActive());
    }

    @Test
    public void beActiveBelowDefaultExpiryOf1000Milliseconds() throws InterruptedException {
        Command testActiveCommand = CommandBuilder.builder().withTargetDeviceName(NEW_COMMAND).build();
        Thread.sleep(500);
        Assert.assertTrue(testActiveCommand.getCommandExpiryMilliseconds() == Command.DEFAULT_EXPIRY_MILLISECONDS);
        Assert.assertTrue(testActiveCommand.isActive());
    }

    @Test
    public void notExpireWhenSetToExpiryOfZeroMilliseconds() throws InterruptedException {
        Command testActiveCommand = CommandBuilder.builder().withTargetDeviceName(NEW_COMMAND).withCommandExpiryMilliseconds(Command.NO_EXPIRY).build();
        Thread.sleep(2000);
        Assert.assertTrue(testActiveCommand.getCommandExpiryMilliseconds() == Command.NO_EXPIRY);
        Assert.assertTrue(testActiveCommand.isActive());
    }

    @Test
    public void accessPayloadFieldGetterAndReturnValue() {
        String payload = "HELLO";
        Command<String> testCommandWithPayload = CommandBuilder.<String>builder().withTargetDeviceName(NEW_COMMAND).withCommandPayload(payload).build();
        Assert.assertThat(testCommandWithPayload.getPayloadFieldValue("bytes"), is(payload.getBytes()));
    }


}
