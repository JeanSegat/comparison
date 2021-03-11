package com.waes.comparison.infrastructure.entrypoint.dto;

import com.sun.istack.NotNull;

public class JsonFileRequestDTO {

    @NotNull
    private String encodedJsonFile;

    public String getEncodedJsonFile() {
        return encodedJsonFile;
    }

    public void setEncodedJsonFile(String encodedJsonFile) {
        this.encodedJsonFile = encodedJsonFile;
    }
}
