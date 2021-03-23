package rd.command.components.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JSONUtil {

    static ObjectMapper mapper = new ObjectMapper();

    public static String toJSONString(Object value) throws JSONObjectException {
        try {
            mapper = new ObjectMapper();
            return mapper.writeValueAsString(value);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JSONObjectException("invalid object to json",
                    e);
        }
    }

    public static String toPrettyJSONString(Object value) throws JSONObjectException {
        try {
            mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(value);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JSONObjectException("invalid object to json", e);
        }
    }

    public static <T> T fromJSONString(String jsonValue, Class<T> to) throws JSONObjectException {
        try {
            mapper = new ObjectMapper();
            return mapper.readValue(jsonValue, to);
        } catch (IOException e) {
            throw new JSONObjectException("invalid json to object", e);
        }
    }
}
