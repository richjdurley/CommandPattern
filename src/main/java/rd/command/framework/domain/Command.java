package rd.command.framework.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Abstract Command to be extended by user defined Items
 *
 * @author Xio
 */
public class Command<P> {

    public static final long DEFAULT_EXPIRY_MILLISECONDS = 1000;
    public static final long NO_EXPIRY = 0;
    private final String targetDeviceName;
    private final String commandActionName;
    private final P commandPayload;
    private final String commandUUID;
    private final long commandTimestamp;
    private final long commandExpiryMilliseconds;

    protected Command(String targetDeviceName, String commandActionName, P commandPayload, String commandUUID, long commandTimestamp, long commandExpiryMilliseconds) {
        this.targetDeviceName = targetDeviceName;
        this.commandActionName = commandActionName;
        this.commandPayload = commandPayload;
        this.commandUUID = commandUUID;
        this.commandTimestamp = commandTimestamp;
        this.commandExpiryMilliseconds = commandExpiryMilliseconds;
    }

    public long commandTimestamp() {
        return this.commandTimestamp;
    }

    public boolean isActive() {
        if (commandExpiryMilliseconds > 0) {
            if (this.commandTimestamp() + (commandExpiryMilliseconds) <= System.currentTimeMillis())
                return false;
            else
                return true;
        } else
            return true;
    }

    public String getTargetDeviceName() {
        return targetDeviceName;
    }

    public String getCommandActionName() {
        return commandActionName;
    }

    public P getCommandPayload() {
        return commandPayload;
    }

    public String getCommandUUID() {
        return commandUUID;
    }

    public long getCommandTimestamp() {
        return commandTimestamp;
    }

    public long getCommandExpiryMilliseconds() {
        return commandExpiryMilliseconds;
    }

    public Object getPayloadFieldValue(String fieldname) {
        Method f = null;
        Object toreturn = null;
        try {
            f = this.commandPayload.getClass()
                    .getMethod("get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1),
                            null);
            toreturn = f.invoke(this.commandPayload, null);
        } catch (NoSuchMethodException | InvocationTargetException e2) {
        } catch (IllegalAccessException e3) {
        }

        return toreturn;
    }
}
