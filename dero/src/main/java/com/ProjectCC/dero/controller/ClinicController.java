package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/api/clinics")
public class ClinicController {

    private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {

        List<Clinic> clinics = clinicService.findAll();

        List<ClinicDTO> clinicDTOS = new ArrayList<>();
        for (Clinic c : clinics) {
            clinicDTOS.add(new ClinicDTO(c));
        }

        return new ResponseEntity<>(clinicDTOS, HttpStatus.OK);
    }

    @PostMapping( consumes = "application/json")
    public ResponseEntity<ClinicDTO> save(@RequestBody ClinicDTO clinicDTO){
            Clinic clinic = new Clinic(clinicDTO);

            clinic = clinicService.save(clinic);
            if(clinic == null){
                return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
            }
            return new ResponseEntity<>(new ClinicDTO(clinic), HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        Clinic clinic = clinicService.findOne(id);

        if (clinic != null) {
            clinicService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClinicDTO> getCourse(@PathVariable Long id) {

        Clinic clinic = clinicService.findOne(id);

        if (clinic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ClinicDTO(clinic), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ClinicDTO> update(@RequestBody ClinicDTO clinicDTO){
        Clinic clinic = new Clinic(clinicDTO);
        clinicService.update(clinic);
        return new ResponseEntity<>(clinicDTO, HttpStatus.OK);

    }
}
