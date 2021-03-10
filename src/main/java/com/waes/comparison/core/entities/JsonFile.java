package com.waes.comparison.core.entities;

import javax.persistence.*;

@Entity
@Table(name="file")
public class JsonFile {

    @Id
    private Long id;

    @Column(name = "right_side")
    @Lob
    private String encodedRightSide;

    @Column(name = "left_side")
    @Lob
    private String encodedLeftSide;

    public Long getId() {
        return id;
    }

    public JsonFile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEncodedRightSide() {
        return encodedRightSide;
    }

    public JsonFile setEncodedRightSide(String encodedRightSide) {
        this.encodedRightSide = encodedRightSide;
        return this;
    }

    public String getEncodedLeftSide() {
        return encodedLeftSide;
    }

    public JsonFile setEncodedLeftSide(String encodedLeftSide) {
        this.encodedLeftSide = encodedLeftSide;
        return this;
    }
}
