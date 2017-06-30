import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandStatus;

public class CommandTestShould {

  class TestCommand extends Command<String> {}

  @Test
  public void haveUUIDOnNewCommand() {
    TestCommand testCommand = new TestCommand();
    Assert.assertNotNull(testCommand.getCommandID());
  }

  @Test
  public void returnStatusSuccessOnComamndSucceeded() {
    TestCommand testCommand = new TestCommand();
    testCommand.commandSucceeded();
    Assert.assertEquals(CommandStatus.SUCCESS, testCommand.getCommandResult().getStatus());
  }

  @Test
  public void returnStatusFailOnCommandFailure() {
    TestCommand testCommand = new TestCommand();
    testCommand.commandFailed();
    Assert.assertEquals(CommandStatus.FAILED, testCommand.getCommandResult().getStatus());
  }
}
