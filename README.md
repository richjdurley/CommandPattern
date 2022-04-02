# The Command Pattern Applied to Java Microservices

In this article we introduce how the command pattern can  be implemented for Java Microservices.

The command pattern enables the abstraction / decoupling of requesting a command from the concrete implementation(s) that action the behaviour of the command. 

It is often used in frameworks or SDKs; an obvious historical example being Java Swing; Action and ActionHandler
classes. A modern example could be within home automation devices such as Alexa, where it is possible to build a routine of commands initiated by a single vocal command.

"Alexa turn my living room orange" - will change all your living rooms lights orange - very nice.

Once executed the command may be successfully processed; partially processed, or failed.

If commands require consistency of multiple states then when errors occur we must implement compensating actions; either using a centralised transaction manager, orchestration engine or using distributed service choreography. 

The choice is largely aligned to the use case architecture you are implementing and tools/sdks/frameworks of choice. 

In this first article we will just use simple commands in a single domain. In the next article we will discuss orchestration and choreography patterns. 

### Base requirements

- Allow any client to send a command to a backend command handler
- A command handler can execute an action compromising of one or more actionable steps to change a single domain's state.
- The command can capture momentos (a log) of the changes applied in each action step (for audit and recovery)
- A command's handler can implement a compensating action when failures occur.
- A command will have a terminal state of succeeded, partially succeeded or failed. 

Example of a basic command class.
```
public class Command<P> {
    private final String id;
    private final String type;
    private final String name;
    private final long createdTimestamp;
    private final long expiryMilliseconds;
    private final String targetResourceURI;
    private final Optional<P> payload;
```
