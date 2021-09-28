package com.challenge.VaccinationCenter;

import com.challenge.domain.model.VaccinationCenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
public class VaccinationCenterTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenNotEmptyVaccinationCenter_thenNoConstraintViolations() {
        VaccinationCenter vaccinationCenter = new VaccinationCenter("53.298810877564875", "-8.997003657335881", "Galway Racecourse");
        Set<ConstraintViolation<VaccinationCenter>> violations = validator.validate(vaccinationCenter);
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    public void whenEmptyLatitude_thenConstraintViolations() {
        VaccinationCenter vaccinationCenter = new VaccinationCenter("", "-8.997003657335881", "Galway Racecourse");
        Set<ConstraintViolation<VaccinationCenter>> violations = validator.validate(vaccinationCenter);
        Assertions.assertEquals(1, violations.size());
    }

    @Test
    public void whenEmptyLatitudeAndLongitude_thenConstraintViolations() {
        VaccinationCenter vaccinationCenter = new VaccinationCenter("", "", "Galway Racecourse");
        Set<ConstraintViolation<VaccinationCenter>> violations = validator.validate(vaccinationCenter);
        Assertions.assertEquals(2, violations.size());
    }

    @Test
    public void whenEmptyLatitudeParameters_thenConstraintViolations() {
        VaccinationCenter vaccinationCenter = new VaccinationCenter();
        Set<ConstraintViolation<VaccinationCenter>> violations = validator.validate(vaccinationCenter);
        Assertions.assertEquals(3, violations.size());
    }
}
