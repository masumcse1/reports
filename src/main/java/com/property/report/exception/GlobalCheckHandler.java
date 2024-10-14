package com.property.report.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalCheckHandler {

        @ExceptionHandler(value = InvalidCredentialsException.class)
        public ResponseEntity<ApiError> invalidCredentials(InvalidCredentialsException exception) {
            return new ResponseEntity<ApiError>(new ApiError(HttpStatus.UNAUTHORIZED, exception.getMessage(),
                    exception), HttpStatus.UNAUTHORIZED);
        }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<ApiError> dataNotFound(DataNotFoundException exception) {
        return new ResponseEntity<ApiError>(new ApiError(HttpStatus.NOT_FOUND, exception.getMessage(),
                exception), HttpStatus.NOT_FOUND);
    }


}
