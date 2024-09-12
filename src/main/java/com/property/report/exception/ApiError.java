package com.property.report.exception;

import org.springframework.http.HttpStatus;

public class ApiError {

    public static final String UNEXPECTED_ERROR = "Unexpected error";
    private int status;
    private String message;
    private String debugMessage;

    ApiError(HttpStatus status, Throwable ex) {
        this(status,UNEXPECTED_ERROR,ex);
    }

    ApiError(String message, Throwable ex) {
        this(HttpStatus.BAD_REQUEST,message,ex);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this.status = status.value();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

}
