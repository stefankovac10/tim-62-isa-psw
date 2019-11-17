package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.model.Medication;
import com.ProjectCC.dero.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/medication")
public class MedicationController {

    private MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Medication>> getAllClinics() {

        List<Medication> clinics = medicationService.findAll();

        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @PostMapping( consumes = "application/json")
    public ResponseEntity<Medication> save(@RequestBody Medication medicationFE){
        Medication medication = new Medication();
        medication.setCode(medicationFE.getCode());
        medication.setName(medicationFE.getName());
        medication.setDescription(medicationFE.getDescription());

        medication = medicationService.save(medication);
        return new ResponseEntity<>(medication, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        Medication medication  = medicationService.findOne(id);

        if (medication != null) {
            medicationService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Medication> getCourse(@PathVariable Long id) {

        Medication medication = medicationService.findOne(id);

        if (medication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(medication, HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Medication> update(@RequestBody Medication medication){
        medicationService.update(medication);
        return new ResponseEntity<>(medication, HttpStatus.OK);
    }
}

