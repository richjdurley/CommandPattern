import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResponse;
import rd.command.framework.service.CommandExecutor;
import rd.command.framework.service.FutureCommandProcessor;
import rd.command.framework.service.FutureTaskCommandProcessor;

import java.util.concurrent.Future;

import static org.hamcrest.CoreMatchers.is;
import static rd.command.framework.domain.CommandStatus.ACCEPTED;

public class CommandExecutorShould {

  public class TestCommand extends Command<String> {}

  public class TestFutureTaskCommandProcessor
      extends FutureTaskCommandProcessor<TestCommand, String> {
    @Override
    public CommandResponse<String> process(TestCommand command) {
      return new CommandResponse<>("HELLO");
    }
  }

  @Test
  public void shouldAcceptACommand() {
    //Given
    FutureCommandProcessor<TestCommand, String> futureCommandProcessor =
        new TestFutureTaskCommandProcessor();
    CommandExecutor<TestCommand, String> commandExecutor =
        new CommandExecutor<>(futureCommandProcessor);
    TestCommand testCommand = new TestCommand();

    //When
    Future<CommandResponse<String>> responseFuture = commandExecutor.execute(testCommand);

    //Then
    Assert.assertThat(testCommand.getProgressLog().get(0).getCommandStatus(), is(ACCEPTED));
  }
}
