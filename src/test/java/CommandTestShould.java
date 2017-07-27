import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.FailedResult;
import rd.command.framework.domain.CommandStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;

public class CommandTestShould {

  public static final String NEW_COMMAND = "NEW_COMMAND";
  public static final String COMMAND_DATA = "COMMAND DATA";

  @Test
  public void haveUUIDOnNewCommand() {
    Command testCommand = new Command();
    Assert.assertNotNull(testCommand.getCommandID());
  }

  @Test
  public void haveACommandNameAndUUIDOnNewCommandWithName() {
    Command testCommand = new Command(NEW_COMMAND);
    Assert.assertThat(testCommand.getCommandName(), is(NEW_COMMAND));
    Assert.assertNotNull(testCommand.getCommandID());
  }

  @Test
  public void haveACommandNameAndDataAndUUIDOnNewCommandWithNameAndData() {
    Command<String> testCommand = new Command<>(NEW_COMMAND,COMMAND_DATA);
    Assert.assertThat(testCommand.getCommandName(), is (NEW_COMMAND));
    Assert.assertThat(testCommand.getCommandData(), is (COMMAND_DATA));
    Assert.assertNotNull(testCommand.getCommandID());
  }

}
