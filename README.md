# Command Pattern example application in Java Microservices

### Rich Durley

In this article we introduce how the command pattern can be used in Microservices and how it differs from events.
Commands should be used in a domain that requires extensible behaviour without knowing ahead what it will be. You will
therefore often see it used in frameworks; an obvious historical example being Java Swing Action and ActionHandler
classes.

In this sense we should cautiously apply the use of Commands in our APIs to those domains that require extensibility
and/or frameworks on which concrete implementations' built. A contained domain would be better represented by API
endpoints with specific signatures.

### What is a command ?

A command is used to decouple a client that requests an action from a handler that invokes the action on a domain
resource(s) that may result in changing the state of a resource. Different

```client -> command -> abstract command handler -> concrete command handler -> changeState.of(stateObjects...)```

e.g. smart_device -> turnOnLightCommand -> LightCommandHandler -> LightA.turnOn()

Command Query Responsibility Segregation (CQRS) extends this pattern to publish state change events form building a read
optimised view. An event publisher could publish to an internal event loop, external message broker or as a result of
database change data capture

```client -> command -> abstract command handler -> concrete command handler -> changeState.of(stateObjects...) -> event -> event publisher |-> event subscriber -> updateReadModelView```

e.g. smart_device -> turnOnLightCommand -> LightCommandHandler -> LightA.turnOn() -> LightTurnedOnEvent

### Attributes of a Command

    Commands can be aggregated into higher level commands e.g. client -> TurnOffGroupCommand(groupName) -> LightGroupHandler -> forEachLight(ingroup).turnOff() 

    Commands can fail if the state of the object they are invoking is invalid

    Commands can be transactional and can exhibit ACID properties, they can support rollback on failure, and may need to update a transactional context where the transaction spans multiple commands

    Commands originate from outside the service boundary (from a client)

    Commands are mutable they have a status and can capture snapshots of state in progress (mementos)

    Commands can implement retry logic

### How is a command different to an event?

    Events originate inside the service boundary

    They are created after a change in state e.g. valid state-> commandRequest -> new valid state change -> event

    They are immutable they represent something that has happened

    They do not capture full state, they only represent the minimal immutable state information about an action that happened

    Events can lead to further changes in state as in Domain Driven Design

    They can be published outside the service boundary (to other clients and services)

    They cannot guarantee transactional semantics as a failed event process cannot be rolled back the domain to the state before the event was published

### When to use a command

    Use when a client does not not need to be aware of the implementation of an object's processing, only on the success or failure of an action on that object, that is possibly in the future

    Use when a client needs to be aware of failure

    When transactional semantics are required

### What is command sourcing

    Command sourcing is when a history of executed commands is stored, and that these commands can be replayed to reconstitute a domain

    An important feature of command sourcing is that replay processing are idempotent and have uniquely identifiable order

    As commands can hold a history of state (momentos) that enable the rebuilding part of a domain in isolation of other internal/external services

    In contrast event sourcing implementations need to capture and replay the history of all events from a know snapshot to reconstitute even part of a domain

### When to use command sourcing

    When a history of state is required

    Where complex recovery requirements exist

    When there is a dependency of state in an external service






















    Client creates the commandRequest

    A commandRequest factory accepts the commandRequest

        the commandRequest is validated

        the factory delegates processing to a task processor <in our example a FutureTask>

        task processor does the processing and updates the status of the commandRequest

    Client checks the status of the commandRequest and retrieves the result


