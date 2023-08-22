package com.empik.demo.error.handlers;

import com.empik.demo.dto.ErrorMessage;
import com.empik.demo.error.errors.UserDoesNotExistException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    private final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    @ExceptionHandler({UserDoesNotExistException.class})
    protected ResponseEntity<ErrorMessage> handleInvalidParameterException(UserDoesNotExistException ex) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(badRequest)
                .error(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorMessage, badRequest);
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException ex) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(internalServerError)
                .error("Some error from Server side")
                .build();

        return new ResponseEntity<>(errorMessage, internalServerError);
    }
}
