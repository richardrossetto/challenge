package com.challenge.service;

import com.challenge.domain.exception.PersonNotFoundException;
import com.challenge.domain.model.Person;
import com.challenge.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void saveAll(List<Person> personList){
        personRepository.saveAll(personList);
    }

    public void save(Person person){
        personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(String.format("Person id %s not found.", id)));
    }
}
