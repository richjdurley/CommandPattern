package rd.device.framework.api.request;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.UUID;

public final class CommandRequestBuilder {
    String requestId = UUID.randomUUID().toString();
    Map<String, Object> headers=CommandDefaults.EMPTY_HEADERS;
    String correlationId = UUID.randomUUID().toString();
    String dataContentType = CommandDefaults.DEFAULT_AS_JSON_CONTENT_TYPE;
    String type = "";
    String name;
    String payload = "";

    private CommandRequestBuilder() {
    }

    public static CommandRequestBuilder aCommandRequest() {
        return new CommandRequestBuilder();
    }

    public CommandRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public CommandRequestBuilder withHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public CommandRequestBuilder withCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public CommandRequestBuilder withDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
        return this;
    }

    public CommandRequestBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CommandRequestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CommandRequestBuilder withPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public CommandRequestBuilder withPayloadBytes(byte[] payload) {;
        this.payload = bytesToStringUTFNIO(payload);
        return this;
    }

    private String bytesToStringUTFNIO(byte[] bytes) {
        CharBuffer cBuffer = ByteBuffer.wrap(bytes).asCharBuffer();
        return cBuffer.toString();
    }

    public CommandRequest build() {
        return new CommandRequest(requestId, headers, correlationId, dataContentType, type, name, payload);
    }
}
