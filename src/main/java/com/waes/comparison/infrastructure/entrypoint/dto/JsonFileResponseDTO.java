package com.waes.comparison.infrastructure.entrypoint.dto;

public class JsonFileResponseDTO {

    private String response;

    public JsonFileResponseDTO(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
