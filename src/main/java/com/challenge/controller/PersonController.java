package com.challenge.controller;

import com.challenge.domain.model.Person;
import com.challenge.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {


    @Autowired
    private PersonService personService;

    @GetMapping("/person-find-all")
    public ResponseEntity findAll() {
        List<Person> personList = personService.findAll();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @PostMapping(value = "/person-list-create")
    @ResponseBody
    public ResponseEntity savePersonList(@Valid @RequestBody List<Person> personList) {
        personService.saveAll(personList);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
