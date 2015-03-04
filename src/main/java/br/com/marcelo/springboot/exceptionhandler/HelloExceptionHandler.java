
package br.com.marcelo.springboot.exceptionhandler;

import java.time.format.DateTimeParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HelloExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DateTimeParseException.class})
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Date format invalid")
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException runtimeException, WebRequest webRequest) {
        return handleException(runtimeException, webRequest);
    }

}
