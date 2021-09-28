package com.challenge.person;

import com.challenge.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
public class PersonTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenNotEmptyPerson_thenNoConstraintViolations() {
        Person person = new Person("53.09402405506328", "-8.020019531250002", "Tayna Durr", 59);
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    public void whenEmptyLongitude_thenConstraintViolations() {
        Person person = new Person("33212", "", "Tayna", 59);
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Assertions.assertEquals(1, violations.size());
    }

    @Test
    public void whenNullLatitudeAndLongitude_thenConstraintViolations() {
        Person person = new Person("", "", "Tayna", 59);
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Assertions.assertEquals(2, violations.size());
    }

    @Test
    public void whenEmptyPersonParameter_thenConstraintViolations() {
        Person person = new Person();
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        Assertions.assertEquals(4, violations.size());
    }
}
