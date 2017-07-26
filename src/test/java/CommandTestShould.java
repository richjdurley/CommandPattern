import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.FailedResult;
import rd.command.framework.domain.CommandStatus;

import static org.hamcrest.CoreMatchers.isA;

public class CommandTestShould {

  private class TestCommand extends Command<String> {}

  @Test
  public void haveUUIDOnNewCommand() {
    TestCommand testCommand = new TestCommand();
    Assert.assertNotNull(testCommand.getCommandID());
  }

}
