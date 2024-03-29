package rd.device.framework.api.exceptions;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;


public class StatusResponse {

    private final int statusCode;
    private final String reason;

    @JsonCreator
    public StatusResponse(@JsonProperty("statusCode") final HttpStatus httpStatus, @JsonProperty("reason") final String reason) {
        this.statusCode = httpStatus.value();
        this.reason = reason;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReason() {
        return reason;
    }

}
