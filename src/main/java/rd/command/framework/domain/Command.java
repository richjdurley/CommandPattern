package rd.command.framework.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Abstract Command to be extended by user defined Items
 *
 * @author Xio
 */
public class Command<P> {
    public static final long DEFAULT_EXPIRY_MILLISECONDS = 1000;
    public static final long NO_EXPIRY = 0;
    private final String targetResourceURI;
    private final String name;
    private final String type;
    private final String id;
    private final long createdTimestamp;
    private final long expiryMilliseconds;
    private final Optional<P> payload;

    protected Command(String targetResourceURI, String name, String type, Optional<P> payload, String id, long createdTimestamp, long expiryMilliseconds) {
        this.targetResourceURI = targetResourceURI;
        this.name = name;
        this.type = type;
        this.payload = payload;
        this.id = id;
        this.createdTimestamp = createdTimestamp;
        this.expiryMilliseconds = expiryMilliseconds;
    }

    public long commandTimestamp() {
        return this.createdTimestamp;
    }

    public boolean isActive() {
        if (expiryMilliseconds > 0) {
            if (this.commandTimestamp() + (expiryMilliseconds) <= System.currentTimeMillis())
                return false;
            else
                return true;
        } else
            return true;
    }

    public String getTargetResourceURI() {
        return targetResourceURI;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Optional<P> getPayload() {
        return payload;
    }

    public String getId() {
        return id;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public long getExpiryMilliseconds() {
        return expiryMilliseconds;
    }

    public Object getPayloadFieldValue(String fieldname) {
        if (this.payload.isPresent()) {
            Method f = null;
            Object toreturn = null;
            try {
                f = this.payload.getClass()
                        .getMethod("get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1),
                                null);
                toreturn = f.invoke(this.payload, null);
            } catch (NoSuchMethodException | InvocationTargetException e2) {
            } catch (IllegalAccessException e3) {
            }
            return toreturn;
        } else return null;
    }
}
