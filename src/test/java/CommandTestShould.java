import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandFailedResult;
import rd.command.framework.domain.CommandStatus;

import static org.hamcrest.CoreMatchers.isA;

public class CommandTestShould {

  private static String RESULT_VALUE = "SUCCEEDED";

  private class TestCommand extends Command<String> {}

  @Test
  public void haveUUIDOnNewCommand() {
    TestCommand testCommand = new TestCommand();
    Assert.assertNotNull(testCommand.getCommandID());
  }

  @Test
  public void returnStatusSuccessOnComamndSucceeded() {
    TestCommand testCommand = new TestCommand();
    testCommand.setSucceeded();
    Assert.assertEquals(CommandStatus.SUCCEEDED, testCommand.getCommandResult().getStatus());
  }

  @Test
  public void returnStatusFailOnCommandFailure() {
    TestCommand testCommand = new TestCommand();
    testCommand.setFailed();
    Assert.assertEquals(CommandStatus.FAILED, testCommand.getCommandResult().getStatus());
  }

  @Test
  public void returnAResultValueOnCommandSucceeded() {
    TestCommand testCommand = new TestCommand();
    testCommand.setSucceeded(RESULT_VALUE);
    Assert.assertEquals(RESULT_VALUE, testCommand.getCommandResult().getResult());
  }

  @Test
  public void returnAnExceptionOnFailure() {
    TestCommand testCommand = new TestCommand();
    testCommand.setFailed();
    Assert.assertThat(testCommand.getCommandResult().getFailedResult(), isA(CommandFailedResult.class));
  }


}
