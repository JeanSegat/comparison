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

    public JsonFile(Long id) {
        this.id = id;
    }

    public JsonFile() {}

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
