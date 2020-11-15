package rd.command.framework.domain;

public class EmptyPayload {

    static EmptyPayload PAYLOAD;

    static {
        PAYLOAD = new EmptyPayload();
    }
}
