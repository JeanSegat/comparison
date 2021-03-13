package com.waes.comparison.core.exception;

public class ComparisonNotFoundException extends RuntimeException{
    public ComparisonNotFoundException() {}

    public ComparisonNotFoundException(String message){
        super(message);
    }
}
