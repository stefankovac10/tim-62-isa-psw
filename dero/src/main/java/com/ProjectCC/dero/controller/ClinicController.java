package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.service.ClinicService;
import org.modelmapper.ModelMapper;
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
    public ClinicController(ModelMapper modelMapper, ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        return clinicService.findAll();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ClinicDTO> save(@RequestBody ClinicDTO clinicDTO){
        return new ResponseEntity<>(clinicService.save(clinicDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        return clinicService.remove(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClinicDTO> getCourse(@PathVariable Long id) {
        return this.clinicService.findById(id);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ClinicDTO> update(@RequestBody ClinicDTO clinicDTO){
        return new ResponseEntity<>(this.clinicService.update(clinicDTO), HttpStatus.OK);

    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<ClinicDTO>> searchClinics(@RequestBody ClinicDTO clinicDTO) {
        return this.clinicService.searchClinics(clinicDTO);
    }

    @GetMapping(value = "/businessReport/{id}")
    public ResponseEntity<ClinicDTO> getReport(@PathVariable Long id) {
        return this.clinicService.businessReport(id);
    }
}
