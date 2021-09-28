package com.challenge.domain.dto;

import java.io.Serializable;

public class PersonDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer age;
    private String distance;

    public PersonDto() {
    }

    public PersonDto(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
