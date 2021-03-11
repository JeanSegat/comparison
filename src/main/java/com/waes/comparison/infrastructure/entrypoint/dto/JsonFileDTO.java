package com.waes.comparison.infrastructure.entrypoint.dto;

public class JsonFileDTO {
    private Long id;

    private String encodedRightSide;

    private String encodedLeftSide;

    public JsonFileDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncodedRightSide() {
        return encodedRightSide;
    }

    public void setEncodedRightSide(String encodedRightSide) {
        this.encodedRightSide = encodedRightSide;
    }

    public String getEncodedLeftSide() {
        return encodedLeftSide;
    }

    public void setEncodedLeftSide(String encodedLeftSide) {
        this.encodedLeftSide = encodedLeftSide;
    }
}
