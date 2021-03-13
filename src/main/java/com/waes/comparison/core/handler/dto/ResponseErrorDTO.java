package com.waes.comparison.core.handler.dto;

public class ResponseErrorDTO {

    private String error;

    public ResponseErrorDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
