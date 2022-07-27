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
        Assert.assertNotNull(testCommand.getId());
        Assert.assertTrue(testCommand.getCreatedTimestamp() > 0);
    }

    @Test
    public void haveACommandWithNewCommandName() {
        Command testCommand = CommandBuilder.builder().withTargetResourceURI(NEW_COMMAND).build();
        Assert.assertThat(testCommand.getName(), is(NEW_COMMAND));
    }

    @Test
    public void haveACommandWithData() {
        Command<?> testCommand = CommandBuilder.builder(COMMAND_DATA).build();
        Assert.assertThat(testCommand.getPayload(), is(COMMAND_DATA));
    }

    @Test
    public void byDefaultExpiresAfter1000Milliseconds() throws InterruptedException {
        Command testExpiredCommand = CommandBuilder.builder().withTargetResourceURI(NEW_COMMAND).build();
        Thread.sleep(1000);
        Assert.assertTrue(!testExpiredCommand.isActive());
    }

    @Test
    public void beActiveBelowDefaultExpiryOf1000Milliseconds() throws InterruptedException {
        Command testActiveCommand = CommandBuilder.builder().withTargetResourceURI(NEW_COMMAND).build();
        Thread.sleep(500);
        Assert.assertTrue(testActiveCommand.getExpiryMilliseconds() == Command.DEFAULT_EXPIRY_MILLISECONDS);
        Assert.assertTrue(testActiveCommand.isActive());
    }

    @Test
    public void notExpireWhenSetToExpiryOfZeroMilliseconds() throws InterruptedException {
        Command testActiveCommand = CommandBuilder.builder().withTargetResourceURI(NEW_COMMAND).withCommandExpiryMilliseconds(Command.NO_EXPIRY).build();
        Thread.sleep(2000);
        Assert.assertTrue(testActiveCommand.getExpiryMilliseconds() == Command.NO_EXPIRY);
        Assert.assertTrue(testActiveCommand.isActive());
    }

    @Test
    public void accessPayloadFieldGetterAndReturnValue() {
        String payload = "HELLO";
        Command<?> testCommandWithPayload = CommandBuilder.builder(payload).withTargetResourceURI(NEW_COMMAND).build();
        Assert.assertThat(testCommandWithPayload.getPayloadFieldValue("bytes"), is(payload.getBytes()));
    }


}
