import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandException;
import rd.command.framework.domain.CommandStatus;
import rd.command.framework.service.CommandProcessor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    testCommand.commandSucceeded();
    Assert.assertEquals(CommandStatus.SUCCESS, testCommand.getCommandResult().getStatus());
  }

  @Test
  public void returnStatusFailOnCommandFailure() {
    TestCommand testCommand = new TestCommand();
    testCommand.commandFailed();
    Assert.assertEquals(CommandStatus.FAILED, testCommand.getCommandResult().getStatus());
  }

  @Test
  public void returnAResultValueOnCommandSucceeded() {
    TestCommand testCommand = new TestCommand();
    testCommand.commandSucceeded(RESULT_VALUE);
    Assert.assertEquals(RESULT_VALUE, testCommand.getCommandResult().getResult());
  }

  @Test
  public void returnAnExceptionOnFailure() {
    TestCommand testCommand = new TestCommand();
    testCommand.commandFailed();
    Assert.assertThat(testCommand.getCommandResult().getException(), isA(CommandException.class));
  }
}
