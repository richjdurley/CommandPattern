package rd.device.framework.api.request;

import java.util.Map;

public class BaseRequest {
    String requestId;
    Map<String, Object> headers;
    String correlationId;
    String dataContentType;
    String type;
    String name;
    String payload;

    public BaseRequest() {
    }

    public BaseRequest(String requestId, Map<String, Object> headers, String correlationId, String dataContentType, String type, String name, String payload) {
        this.requestId = requestId;
        this.headers = headers;
        this.correlationId = correlationId;
        this.dataContentType = dataContentType;
        this.type=type;
        this.name = name;
        this.payload = payload;
    }

    public String getRequestId() {
        return requestId;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getDataContentType() {
        return dataContentType;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseRequest that = (BaseRequest) o;

        return requestId.equals(that.requestId);
    }

    @Override
    public int hashCode() {
        return requestId.hashCode();
    }
}
