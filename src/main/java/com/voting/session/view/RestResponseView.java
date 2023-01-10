package com.voting.session.view;

import org.springframework.http.HttpStatus;

public class RestResponseView {

    private Object response;

    private HttpStatus httpStatus;

    private String error;

    public RestResponseView() {
    }

    public RestResponseView(Object response, String error, HttpStatus httpStatus) {
        this.response = response;
        this.error = error;
        this.httpStatus = httpStatus;
    }

    public RestResponseView(Object response, HttpStatus httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }

    public RestResponseView(HttpStatus httpStatus, String error) {
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResponse() {
        return response;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
