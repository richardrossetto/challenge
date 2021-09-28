package com.challenge.controller;

import com.challenge.domain.model.VaccinationCenter;
import com.challenge.service.VaccinationCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class VaccinationCenterController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    @GetMapping("/vaccination-center-find-all")
    public ResponseEntity findAll() {
        List<VaccinationCenter> vaccinationCenters = vaccinationCenterService.findAll();
        return new ResponseEntity<>(vaccinationCenters, HttpStatus.OK);
    }

    @PostMapping("/vaccination-center-list-create")
    @ResponseBody
    public ResponseEntity saveVaccinationCenterList(@Valid @RequestBody List<VaccinationCenter> vaccinationCenterList) {
        vaccinationCenterService.saveAll(vaccinationCenterList);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/vaccination-center/find-people-nearby")
    public ResponseEntity findPeopleNearby() {
        return new ResponseEntity<>(vaccinationCenterService.findPeopleNearVaccinationCenter(), HttpStatus.OK);
    }
}
