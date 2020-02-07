package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.MedicationDTO;
import com.ProjectCC.dero.model.Medication;
import com.ProjectCC.dero.service.MedicationService;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    public MedicationController(MedicationService medicationService, ModelMapper modelMapper) {
        this.medicationService = medicationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<MedicationDTO>> getAllMedication() {

        List<MedicationDTO> medicationDTOS = medicationService.findAll();

        return new ResponseEntity<>(medicationDTOS, HttpStatus.OK);

    }

    @GetMapping(value = "/all/{page}")
    public ResponseEntity<List<MedicationDTO>> getAllMedication(@PathVariable int page) {

        List<MedicationDTO> medications = medicationService.findAll(page);

        return new ResponseEntity<>(medications, HttpStatus.OK);

    }

    @PostMapping( consumes = "application/json")
    public ResponseEntity<MedicationDTO> save(@RequestBody MedicationDTO medicationDTO){
        Medication medication = new Medication(medicationDTO);

        medication = medicationService.save(medication);
        if(medication == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelMapper.map(medication, MedicationDTO.class), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        return medicationService.remove(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> getCourse(@PathVariable Long id) {

        Medication medication = medicationService.findOne(id);

        if (medication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(modelMapper.map(medication,MedicationDTO.class), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<MedicationDTO> update(@RequestBody MedicationDTO medicationDTO){
        Medication medication = new Medication(medicationDTO);
        medicationService.update(medication);
        return new ResponseEntity<>(medicationDTO, HttpStatus.OK);
    }
}

