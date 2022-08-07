package rd.device.framework.domain;

public class FailedResult {

    String message;
    Throwable cause;

    public FailedResult() {
        this.message = "Command failedResult occurred";
    }

    public FailedResult(String message) {
        this.message = message;
    }

    public FailedResult(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}
