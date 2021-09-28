package com.challenge.domain.model;

import com.challenge.domain.dto.PersonDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Person extends Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @JsonProperty("Name")
    private String name;

    @NotNull(message = "Age is mandatory")
    @JsonProperty("Age")
    private Integer age;

    public Person() {
    }

    public Person(Long id, String latitude, String longitude, String name, Integer age) {
        this(latitude, longitude, name, age);
        this.id = id;
    }

    public Person(String latitude, String longitude, String name, Integer age) {
        super(latitude, longitude);
        this.name = name;
        this.age = age;
    }

    public PersonDto toDto() {
        return new PersonDto(this.id, this.name, this.age);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                '}';
    }
}
