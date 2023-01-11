package com.voting.session.handler;

import com.voting.session.utils.RestApiErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String error = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();

        RestApiErrorMessage restApiErrorMessage = new RestApiErrorMessage(error, HttpStatus.valueOf(status.value()));

        return new ResponseEntity<>(restApiErrorMessage, restApiErrorMessage.getHttpStatus());
    }

    public ResponseEntity<RestApiErrorMessage> handleExceptionNotFound(Exception exception, WebRequest request) {

        RestApiErrorMessage restApiErrorMessage = new RestApiErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(restApiErrorMessage, new HttpHeaders(), restApiErrorMessage.getHttpStatus());

    }

    public ResponseEntity<RestApiErrorMessage> handleBadRequestExceptions(Exception exception, WebRequest request) {

        RestApiErrorMessage restApiErrorMessage = new RestApiErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(restApiErrorMessage, new HttpHeaders(), restApiErrorMessage.getHttpStatus());

    }


}
