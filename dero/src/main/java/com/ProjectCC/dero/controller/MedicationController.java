package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.MedicationDTO;
import com.ProjectCC.dero.model.Medication;
import com.ProjectCC.dero.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/api/medication")
public class MedicationController {

    private MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<MedicationDTO>> getAllMedication() {

        List<Medication> medications = medicationService.findAll();

        List<MedicationDTO> medicationDTOS = new ArrayList<>();
        for (Medication m : medications) {
            medicationDTOS.add(new MedicationDTO(m));
        }

        return new ResponseEntity<>(medicationDTOS, HttpStatus.OK);

    }

    @PostMapping( consumes = "application/json")
    public ResponseEntity<MedicationDTO> save(@RequestBody MedicationDTO medicationDTO){
        Medication medication = new Medication(medicationDTO);

        medication = medicationService.save(medication);
        if(medication == null){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(new MedicationDTO(medication), HttpStatus.CREATED);
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
    public ResponseEntity<MedicationDTO> getCourse(@PathVariable Long id) {

        Medication medication = medicationService.findOne(id);

        if (medication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MedicationDTO(medication), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<MedicationDTO> update(@RequestBody MedicationDTO medicationDTO){
        Medication medication = new Medication(medicationDTO);
        medicationService.update(medication);
        return new ResponseEntity<>(medicationDTO, HttpStatus.OK);
    }
}

