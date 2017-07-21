Command Pattern And Command Sourcing
Rich Durley @ June 2017

What is a commandRequest.

    A commandRequest is used to decouple a client that invokes an action from the one that needs to perform it e.g. client -> turnOnLightCmd -> Light.PressSwitch()

Attributes of a Command

    Commands are composable e.g. ReplaceBulbCmd = TurnOffLight + RemoveBulb + AddBulb + TurnOnLight

    Commands can fail if the state of the object they are invoking is invalid

    Commands can be transactional and can exhibit ACID properties, they can support rollback on failure

    Commands originate from outside the service boundary (from a client)

    Commands are mutable they have a status and can capture snapshots of state in progress (mementos)

    Commands can implement retry logic

How is this different to an event?

    Events originate inside the service boundary

    They are created after a change in state e.g. valid state-> commandRequest -> new valid state change -> event

    They are immutable they represent something that has happened

    They do not capture full state, they only represent the minimal immutable state information about an action that happened

    Events can lead to further changes in state as in Domain Driven Design

    They can be published outside the service boundary (to other clients and services)

    They cannot guarantee transactional semantics as a failed event process cannot be rolled back the domain to the state before the event was published

When to use a commandRequest

    Use when a client does not not need to be aware of the implementation of an object's processing, only on the success or failure of an action on that object

    Use when a client needs to be aware of failure

    When transactional semantics are required

What is commandRequest sourcing

    Command sourcing is when a history of executed commands is stored, and that these commands can be replayed to reconstitute a domain

    An important feature of commandRequest sourcing is that commandRequest replay processing is that commands should be idempotent and have uniquely identifiable order

    As commands can hold a history of state (momentos) that enables the rebuilding part of a domain in isolation of other internal/external services

    In contrast event sourcing implementations need to capture and replay the history of all events from a know snapshot to reconstitute even part of a domain

When to use commandRequest sourcing

    When a history of state is required

    Where complex recovery requirements exist

    When the is a dependency of state in an external service






















    Client creates the commandRequest

    A commandRequest factory accepts the commandRequest

        the commandRequest is validated

        the factory delegates processing to a task processor <in our example a FutureTask>

        task processor does the processing and updates the status of the commandRequest

    Client checks the status of the commandRequest and retrieves the result


