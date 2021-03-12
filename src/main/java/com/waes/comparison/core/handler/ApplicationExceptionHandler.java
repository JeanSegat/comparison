package com.waes.comparison.core.handler;

import com.waes.comparison.core.exception.FileNotFoundException;
import com.waes.comparison.core.exception.OneFileIsEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({FileNotFoundException.class})
    public ResponseEntity handleFileNotFoundException(Exception exception) {
        return handleExceptionResponse(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({OneFileIsEmptyException.class})
    public ResponseEntity handleOneFileIsEmptyException(Exception exception) {
        return handleExceptionResponse(exception, HttpStatus.NOT_ACCEPTABLE);
    }

    private ResponseEntity<String> handleExceptionResponse(Exception exception, HttpStatus status) {
        return new ResponseEntity<>(exception.getMessage(),status);
    }
}
