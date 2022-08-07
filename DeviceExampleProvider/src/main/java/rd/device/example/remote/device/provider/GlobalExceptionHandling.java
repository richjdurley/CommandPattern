package rd.device.example.remote.device.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rd.device.example.remote.device.provider.model.ErrorResponse;
import rd.device.example.remote.device.provider.domain.LightException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandling {
    final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandling.class);

    @ExceptionHandler(LightException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse exceptionHandler(LightException exception) {
        return new ErrorResponse(BAD_REQUEST.value(),exception.getMessage());
    }
}
