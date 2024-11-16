package academy.atl.customers.controller.handler;

import academy.atl.customers.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidEmailException.class, MinimumAmountOfCharactersException.class, RequiredFieldException.class})
    protected ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        CustomApiError error = new CustomApiError();
        String nombreException = ex.getClass().getSimpleName();
        error.agregarInfoException(nombreException, ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {CustomerNotFoundException.class, UserNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        CustomApiError error = new CustomApiError();
        String nombreException = ex.getClass().getSimpleName();
        error.agregarInfoException(nombreException, ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (body == null) {
            CustomApiError error = new CustomApiError();
            String exceptionName = ex.getClass().getSimpleName();
            error.agregarInfoException(exceptionName, ex.getMessage());
            body = error;
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
