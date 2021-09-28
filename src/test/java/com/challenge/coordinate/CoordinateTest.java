package com.challenge.coordinate;

import com.challenge.domain.dto.VaccinationCenterDto;
import com.challenge.domain.model.Person;
import com.challenge.domain.model.VaccinationCenter;
import com.challenge.service.VaccinationCenterService;
import com.challenge.util.CoordinateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CoordinateTest {

    @InjectMocks
    private VaccinationCenterService vaccinationCenterService;

    @Test
    public void calculateDistanceBetween2Points() {
        Person person = new Person("53.09402405506328", "-8.020019531250002", "Tayna Durr", 59);
        VaccinationCenter vaccinationCenter = new VaccinationCenter("53.298810877564875", "-8.997003657335881", "Galway Racecourse");
        double distance = vaccinationCenterService.getDistance(person, vaccinationCenter);
        Assertions.assertEquals("68.95Km", CoordinateUtil.formatDistanceToStringKm(distance));
    }

    @Test
    public void findPeopleNearVaccinationCenter() {
        Person person = new Person(1l, "53.09402405506328", "-8.020019531250002", "Tayna Durr", 59);
        Person person1 = new Person(2l, "52.83525354194617", "-6.610572771365111", "Talitha Laird", 71);
        Person person2 = new Person(3l, "53.10201460568079", "-6.236841329955483", "Rochel Hardrick", 44);
        Person person3 = new Person(4l, "53.60597741344063", "-6.271446093048965", "Pa Sheckler", 67);
        Person person4 = new Person(5l, "52.27984782255751", "-9.205930070018244", "Marianna Strader", 70);
        VaccinationCenter vaccinationCenter = new VaccinationCenter(1l, "53.298810877564875", "-8.997003657335881", "Galway Racecourse");
        VaccinationCenter vaccinationCenter1 = new VaccinationCenter(2l, "51.89742637092438", "-8.465763459121026", "City Hall Cork");
        VaccinationCenter vaccinationCenter2 = new VaccinationCenter(3l, "53.28603418885669", "-6.4444477725802285", "Citywest Convention Centre Dublin");

        List<Person> personList = Arrays.asList(person, person1, person2, person3, person4);
        List<VaccinationCenter> vaccinationCenterList = Arrays.asList(vaccinationCenter, vaccinationCenter1, vaccinationCenter2);

        List<VaccinationCenterDto> vaccinationCenterDtos = vaccinationCenterService.findPeopleByVaccinationCenter(personList, vaccinationCenterList);

        Assertions.assertEquals(3, vaccinationCenterDtos.size());
        Assertions.assertEquals(1, vaccinationCenterDtos.get(0).getPersonDtos().size());
        Assertions.assertEquals(1, vaccinationCenterDtos.get(1).getPersonDtos().size());
        Assertions.assertEquals(3, vaccinationCenterDtos.get(2).getPersonDtos().size());
    }
}
