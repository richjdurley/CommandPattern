package rd.device.framework.domain;


import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

/**
 * Abstract Command to be extended by user defined Items
 *
 * @author Xio
 */
public class Command {
    public static final long DEFAULT_EXPIRY_MILLISECONDS = 1000;
    public static final long NO_EXPIRY = 0;
    private final String deviceResourceURI;
    private final String name;
    private final String type;
    private final String id;
    private final long createdTimestamp;
    private final long expiryMilliseconds;
    private final String payload;
    private final String contentType;

    protected Command(String deviceResourceURI, String name, String type, String payload, String payloadRawContentType, String id, long createdTimestamp, long expiryMilliseconds){
        this.id = id;
        this.deviceResourceURI = deviceResourceURI;
        this.name = name;
        this.type = type;
        if (payload != null && !payload.isBlank()) {
            try {
                JsonParser jsonParser = JsonParserFactory.getJsonParser();
                jsonParser.parseMap(payload);
                this.payload = payload;
            } catch (JsonParseException e) {
                throw new RuntimeException("invalid json key value payload list");
            }
        } else this.payload = "{}";
        this.createdTimestamp = createdTimestamp;
        this.expiryMilliseconds = expiryMilliseconds;
        this.contentType = payloadRawContentType;
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

    public String getDeviceResourceURI() {
        return deviceResourceURI;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
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

    public Object getPayloadFieldValue(String fieldName) {
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        return jsonParser.parseMap(this.payload).get("fieldName");
    }

}
