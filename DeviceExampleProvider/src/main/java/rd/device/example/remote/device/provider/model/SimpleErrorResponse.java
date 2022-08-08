package rd.device.example.remote.device.provider.model;

public final class SimpleErrorResponse {
    final int status;
    final String message;

    public SimpleErrorResponse(int value, String message) {
        this.status = value;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
