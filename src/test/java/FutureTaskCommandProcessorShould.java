import org.junit.Assert;
import org.junit.Test;
import rd.command.framework.CommandHandler;
import rd.command.framework.FutureTaskCommandHandler;
import rd.command.framework.domain.Command;
import rd.command.framework.domain.CommandResult;
import rd.command.framework.domain.FailedResult;

import java.util.concurrent.Future;

import static org.hamcrest.CoreMatchers.is;
import static rd.command.framework.domain.CommandStatus.FAILED;
import static rd.command.framework.domain.CommandStatus.SUCCEEDED;

public class FutureTaskCommandProcessorShould {

    public static final String RESULT = "HELLO";
    public static final String TEST_FAILURE_REASON = "TEST FAILURE REASON";
    FutureTaskCommandHandler<TestCommand, String> futureCommandProcessor =
            new FutureTaskCommandHandler<>();

    @Test
    public void shouldSuccessfullyProcessACommand() throws Exception {
        //Given
        TestCommand testCommand = new TestCommand();

        //When
        Future<CommandResult<String>> commandResultFuture =
                futureCommandProcessor.accept(testCommand, new SuccessfulCommandProcessor());

        //Then
        Assert.assertThat(commandResultFuture.get().getStatus(), is(SUCCEEDED));
        Assert.assertThat(commandResultFuture.get().getSuccessResult(), is(RESULT));
    }

    @Test
    public void shouldFailToProcessACommand() throws Exception {
        //Given
        TestCommand testCommand = new TestCommand();

        //When
        Future<CommandResult<String>> commandResultFuture =
                futureCommandProcessor.accept(testCommand, new FailedCommandProcessor());

        //Then
        Assert.assertThat(commandResultFuture.get().getStatus(), is(FAILED));
        Assert.assertThat(
                commandResultFuture.get().getFailedResult().getMessage(), is(TEST_FAILURE_REASON));
    }

    public class TestCommand extends Command<String> {
    }

    public class SuccessfulCommandProcessor implements CommandHandler<TestCommand, String> {
        @Override
        public CommandResult<String> handle(TestCommand command) {
            return new CommandResult<String>(RESULT);
        }
    }

    public class FailedCommandProcessor implements CommandHandler<TestCommand, String> {
        @Override
        public CommandResult<String> handle(TestCommand command) {
            return new CommandResult<>(new FailedResult(TEST_FAILURE_REASON));
        }
    }
}
