package rd.device.util.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rd.device.framework.api.exceptions.BadRequestException;
import rd.device.framework.api.handler.SimpleErrorResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandling {
    final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandling.class);

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public SimpleErrorResponse exceptionHandler(BadRequestException exception) {
        return new SimpleErrorResponse(BAD_REQUEST.value(), exception.getMessage());
    }
}
