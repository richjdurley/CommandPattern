# The Command Pattern Applied to Java Microservices

In this article we will discuss how command, gateway and hexagonal architecture patterns can be used when designing and delivering Automation use cases 
e.g. Home Automation, IIOT, Robotics, Smart Cities etc

The command pattern enables the decoupling o a client requesting a command from the concrete implementation that executes the command.
It is important that the implementation of "execution" of the command should not be visible or matter to the requester.

The gateway pattern provides routing capabilities from one canonical public interface (port) to many specific physical implementations (adaptors). 

Many modern examples are already here for home automation, robotics and IOT devices.

Generally we expect commands to be asynchronous. And we expect the result of a command to be provided some time in the future.

If commands require consistency across multiple domain's then when errors occur we must implement compensating actions; either
using a centralised transaction manager, orchestration engine or using distributed service choreography. The choice here is largely 
aligned to your architecture design approach & frameworks of choice.

In this first article we will just use simple commands in a single domain. In the next article we will discuss
orchestration and choreography patterns.

### Base requirements

- Allow a client to send a generic command to a command handler acting as a gateway to a number of device implementations e.g. light device
- Create a concrete command handler that executes the command on the remote resource 
- Asynchronously return the command state.
- The command will have a terminal state of succeeded, partially succeeded, failed, unknown.

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
