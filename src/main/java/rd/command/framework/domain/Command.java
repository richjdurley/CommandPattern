package rd.command.framework.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Abstract Command to be extended by user defined Items
 *
 * @author Xio
 */
public class Command<P> {

    public static final int COMMAND_EXPIRY_MILLISECONDS = 3000;
    private final String commandName;
    private final P commandPayload;
    private final String commandUUID;
    private final long commandTimestamp;
    private final long commandExpiryMilliseconds;

    protected Command(String commandName, P commandPayload, String commandUUID, long commandTimestamp, long commandExpiryMilliseconds) {
        this.commandName = commandName;
        this.commandPayload = commandPayload;
        this.commandUUID = commandUUID;
        this.commandTimestamp = commandTimestamp;
        this.commandExpiryMilliseconds = commandExpiryMilliseconds;
    }

    public long commandTimestamp() {
        return this.commandTimestamp;
    }

    public boolean expiresAfter() {
        if (commandExpiryMilliseconds > 0) {
            if (this.commandTimestamp() + (commandExpiryMilliseconds * 1000) > System.currentTimeMillis())
                return true;
            else
                return false;
        } else
            return true;
    }

    public boolean readyForHouseKeeping(long maxTTLSeconds) {
        if (this.commandTimestamp() + (maxTTLSeconds * 1000) > System.currentTimeMillis())
            return true;
        else
            return false;
    }

    public String getCommandName() {
        return commandName;
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
