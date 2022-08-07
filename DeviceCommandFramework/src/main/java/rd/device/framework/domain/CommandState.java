package rd.device.framework.domain;

public class CommandState<R> {
    CommandStates status;
    R result;
    FailedResult failedResult;

    public CommandState(R result) {
        this.status = CommandStates.SUCCEEDED;
        this.result = result;
    }

    public CommandState(FailedResult failedResult) {
        this.status = CommandStates.FAILED;
        this.failedResult = failedResult;
    }

    public CommandStates getStatus() {
        return status;
    }

    public R getSuccessResult() {
        return result;
    }

    public void setSuccessResult(R result) {
        this.result = result;
    }

    public FailedResult getFailedResult() {
        return failedResult;
    }

    public void setFailedResult(FailedResult failedResult) {
        this.failedResult = failedResult;
    }
}
