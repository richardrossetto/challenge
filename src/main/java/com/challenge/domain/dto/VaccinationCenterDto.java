package com.challenge.domain.dto;

import com.challenge.domain.model.VaccinationCenter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VaccinationCenterDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String latitude;

    private String longitude;

    @JsonProperty("people")
    private List<PersonDto> personDtos = new ArrayList<>();

    public VaccinationCenterDto() {
    }

    public VaccinationCenterDto(VaccinationCenter vaccinationCenter, List<PersonDto> personDtos) {
        this.name = vaccinationCenter.getName();
        this.latitude = vaccinationCenter.getLatitude();
        this.longitude = vaccinationCenter.getLongitude();
        this.personDtos = personDtos;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public List<PersonDto> getPersonDtos() {
        return personDtos;
    }
}
