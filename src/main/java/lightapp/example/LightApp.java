package lightapp.example;

import lightapp.example.domain.Light;
import lightapp.example.domain.LightState;
import lightapp.example.domain.command.LightCommand;
import lightapp.example.domain.command.TurnLightOffCommand;
import lightapp.example.domain.command.TurnLightOnCommand;
import lightapp.example.service.LightCommandProcessorService;

import java.util.concurrent.FutureTask;

/**
 * DEMO COMMAND PATTERN USING LIGHT ON AND OFF COMMANDS, NOTE AS THESE COMMANDS ARE SENT CONCURRENTLY
 * THEY CAN FAIL IF THE SECOND COMMAND GETS EXECUTED BEFORE THE FIRST COMMAND!
 */
public class LightApp {

  public static void main(String args[]) throws Exception {

    Light light = new Light(LightState.OFF);
    LightCommandProcessorService lightCommandProcessor = new LightCommandProcessorService(light);

    try {
      System.out.println("SENDING: LIGHT ON COMMAND 1");
      FutureTask<LightCommand> command1status =
          lightCommandProcessor.accept(new TurnLightOnCommand());

      System.out.println("SENDING: LIGHT OFF COMMAND 2");
      FutureTask<LightCommand> command2status =
          lightCommandProcessor.accept(new TurnLightOffCommand());

      while (!command1status.isDone() && !command2status.isDone()) {
        Thread.sleep(100);
      }
      System.out.println(
          "LIGHT ON COMMAND 1 STATUS: " + command1status.get().getCommandResult().getStatus());
      System.out.println(
          "LIGHT ON COMMAND 1 RESULT: " + command1status.get().getCommandResult().getResult());
      System.out.println(
          "LIGHT OFF COMMAND 2 STATUS: " + command2status.get().getCommandResult().getStatus());
      System.out.println(
          "LIGHT OFF COMMAND 2 RESULT: " + command2status.get().getCommandResult().getResult());
    } finally {
      lightCommandProcessor.close();
    }
  }
}
