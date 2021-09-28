package com.challenge.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@MappedSuperclass
public abstract class Coordinate implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;

    @NotBlank(message = "Latitude is mandatory")
    @JsonProperty("Latitude")
    private String latitude;

    @NotBlank(message = "Longitude is mandatory")
    @JsonProperty("Longitude")
    private String longitude;

    public Coordinate() {}

    public Coordinate(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }


}
