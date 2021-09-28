package com.challenge.service;

import com.challenge.domain.exception.PersonException;
import com.challenge.domain.exception.PersonNotFoundException;
import com.challenge.domain.model.Person;
import com.challenge.domain.model.VaccinationCenter;
import com.challenge.domain.exception.VaccinationCenterException;
import com.challenge.domain.dto.PersonDto;
import com.challenge.domain.dto.VaccinationCenterDto;
import com.challenge.repository.VaccinationCenterRepository;
import com.challenge.util.CoordinateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class VaccinationCenterService {

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    @Autowired
    private PersonService personService;

    public void saveAll(List<VaccinationCenter> vaccinationCenters) {
        if (vaccinationCenters.isEmpty())
            throw new VaccinationCenterException("Vaccination Center is Empty.");
        vaccinationCenterRepository.saveAll(vaccinationCenters);
    }

    public List<VaccinationCenter> findAll() {
        return vaccinationCenterRepository.findAll();
    }

    public List<VaccinationCenterDto> findPeopleNearVaccinationCenter() {
        List<VaccinationCenter> vaccinationCenterList = vaccinationCenterRepository.findAll();
        List<Person> personList = personService.findAll();
        List<VaccinationCenterDto> dto = findPeopleByVaccinationCenter(personList, vaccinationCenterList);
        return dto;
    }

    public List<VaccinationCenterDto> findPeopleByVaccinationCenter(List<Person> personList, List<VaccinationCenter> vaccinationCenterList) {
        if(personList.isEmpty())
            throw new PersonException("Person list is empty.");
        if (vaccinationCenterList.isEmpty())
            throw new VaccinationCenterException("Vaccination Center list is empty");
        Map<VaccinationCenter, List<PersonDto>> map = new HashMap<>();
        List<VaccinationCenterDto> dto = new ArrayList<>();
        personList.stream().forEach(person -> findPeopleNearby(map, person, vaccinationCenterList));
        dto = convertMapToVaccinationCenterDto(map);
        return dto;
    }

    private List<VaccinationCenterDto> convertMapToVaccinationCenterDto(Map<VaccinationCenter, List<PersonDto>> map) {
        return map.entrySet().stream().map(x -> new VaccinationCenterDto(x.getKey(), x.getValue())).collect(Collectors.toList());
    }

    private void findPeopleNearby(Map<VaccinationCenter, List<PersonDto>> map, Person person, List<VaccinationCenter> vaccinationCenterList) {
        AtomicReference<VaccinationCenter> vaccinationCenter = new AtomicReference<>();
        AtomicReference<Double> distance = new AtomicReference<>(0.0);
        vaccinationCenterList.stream().forEach(v -> {
            double currentDistance = getDistance(person, v);
            if (distance.get() == 0.0 || currentDistance < distance.get()) {
                distance.set(currentDistance);
                vaccinationCenter.set(v);
            }
        });

        PersonDto personDto = person.toDto();
        personDto.setDistance(CoordinateUtil.formatDistanceToStringKm(distance.get()));

        if (!map.containsKey(vaccinationCenter.get())) {
            map.put(vaccinationCenter.get(), Arrays.asList(personDto));
        } else {
            List<PersonDto> personDtoList = new ArrayList<>(map.get(vaccinationCenter.get()));
            personDtoList.add(personDto);

            Collections.sort(personDtoList, Comparator.comparing(PersonDto::getAge).reversed()
                    .thenComparing(PersonDto::getName));
            map.put(vaccinationCenter.get(), personDtoList);
        }
    }

    public double getDistance(Person person, VaccinationCenter vaccinationCenter) {
        return CoordinateUtil.distance(Double.parseDouble(person.getLatitude()), Double.parseDouble(person.getLongitude()),
                Double.parseDouble(vaccinationCenter.getLatitude()), Double.parseDouble(vaccinationCenter.getLongitude()), 'K');
    }

}
