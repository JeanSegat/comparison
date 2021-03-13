package com.waes.comparison.infrastructure.entrypoint.dto;

import javax.validation.constraints.NotEmpty;

public class JsonFileRequestDTO {

    @NotEmpty(message = "Please send a file")
    private String encodedJsonFile;

    public String getEncodedJsonFile() {
        return encodedJsonFile;
    }

    public void setEncodedJsonFile(String encodedJsonFile) {
        this.encodedJsonFile = encodedJsonFile;
    }
}
