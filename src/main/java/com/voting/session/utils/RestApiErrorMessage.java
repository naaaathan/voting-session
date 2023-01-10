package com.voting.session.utils;

import org.springframework.http.HttpStatus;

import java.util.List;

public class RestApiErrorMessage {

    private String error;
    private HttpStatus httpStatus;

    public RestApiErrorMessage(String error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
