package com.waes.comparison.core.handler;

import com.waes.comparison.core.exception.ComparisonNotFoundException;
import com.waes.comparison.core.exception.OneFileIsEmptyException;
import com.waes.comparison.core.handler.dto.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({ComparisonNotFoundException.class})
    public ResponseEntity handleFileNotFoundException(Exception exception) {
        return handleExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({OneFileIsEmptyException.class})
    public ResponseEntity handleOneFileIsEmptyException(Exception exception) {
        return handleExceptionResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder result = new StringBuilder();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
             result.append(error.getDefaultMessage());
        });
        return handleExceptionResponse(result.toString(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ResponseErrorDTO> handleExceptionResponse(String message, HttpStatus status) {
        return new ResponseEntity<ResponseErrorDTO>(new ResponseErrorDTO(message),status);
    }
}
