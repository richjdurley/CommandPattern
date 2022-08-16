package rd.device.framework.api.request;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class CommandDefaults {
    //30 seconds
    public static long COMMAND_TIMEOUT = 30000;
    public static Map<String, Object> EMPTY_HEADERS = new HashMap<>();
    public static String DEFAULT_AS_JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
}
