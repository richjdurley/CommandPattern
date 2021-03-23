package rd.command.components.api.framework.exceptions;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;


public class ErrorResponse {

    private final int statusCode;
    private final String reason;

    @JsonCreator
    public ErrorResponse(@JsonProperty("statusCode") final HttpStatus httpStatus, @JsonProperty("reason") final String reason) {
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
