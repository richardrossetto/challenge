package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class VaccinationCenter extends Coordinate {
    ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @JsonProperty("Name")
    private String name;

    public VaccinationCenter() {
    }

    public VaccinationCenter(Long id, String latitude, String longitude, String name) {
        this(latitude, longitude, name);
        this.id = id;
    }

    public VaccinationCenter(String latitude, String longitude, String name) {
        super(latitude, longitude);
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccinationCenter that = (VaccinationCenter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
