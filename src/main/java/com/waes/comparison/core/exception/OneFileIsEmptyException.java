package com.waes.comparison.core.exception;

public class OneFileIsEmptyException extends RuntimeException {
    public OneFileIsEmptyException() {}

    public OneFileIsEmptyException(String message) {
        super(message);
    }
}
