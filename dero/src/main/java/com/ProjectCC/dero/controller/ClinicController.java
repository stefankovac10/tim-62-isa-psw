package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.dto.ClinicWithSpecilizedTypeDTO;
import com.ProjectCC.dero.service.ClinicService;
import org.hibernate.StaleObjectStateException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @PreAuthorize("hasRole('ROLE_CCADMIN') || hasRole('ROLE_PATIENT')  || hasRole('ROLE_CADMIN') || hasRole('ROLE_DOCTOR') || hasRole('ROLE_NURSE')")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        return clinicService.getAll();
    }

    @GetMapping(value = "/all/{page}")
    @PreAuthorize("hasRole('ROLE_CCADMIN') || hasRole('ROLE_PATIENT')  || hasRole('ROLE_CADMIN') || hasRole('ROLE_DOCTOR') || hasRole('ROLE_NURSE')")
    public ResponseEntity<List<ClinicDTO>> getAllClinics(@PathVariable int page) {
        return clinicService.findAll(page);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize(" hasRole('ROLE_CCADMIN')")
    public ResponseEntity<ClinicDTO> save(@RequestBody ClinicDTO clinicDTO){
        ClinicDTO clinic = clinicService.save(clinicDTO);
        if(clinic == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(clinic, HttpStatus.CREATED);
        }

    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize(" hasRole('ROLE_CCADMIN')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        return clinicService.remove(id);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_CCADMIN') || hasRole('ROLE_CADMIN') || hasRole('ROLE_DOCTOR') || hasRole('ROLE_NURSE')")
    public ResponseEntity<ClinicDTO> getCourse(@PathVariable Long id) {
        return this.clinicService.findById(id);
    }

    @PutMapping(consumes = "application/json")
    @PreAuthorize(" hasRole('ROLE_CCADMIN')")
    public ResponseEntity<ClinicDTO> update(@RequestBody ClinicDTO clinicDTO){
        try {
            return this.clinicService.update(clinicDTO);
        } catch (StaleObjectStateException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Someone is already editing this clinic.", e);
        }
    }

    @PostMapping(value = "/search")
    @PreAuthorize("!hasRole('ROLE_REQUEST')")
    public ResponseEntity<List<ClinicDTO>> searchClinics(@RequestBody ClinicDTO clinicDTO) {
        return this.clinicService.searchClinics(clinicDTO);
    }

    @GetMapping(value = "/businessReport/{id}")
    @PreAuthorize(" hasRole('ROLE_CADMIN')")
    public ResponseEntity<ClinicDTO> getReport(@PathVariable Long id) {
        return this.clinicService.businessReport(id);
    }

    @GetMapping(value = "/search2/{date}/{type}")
    public ResponseEntity<List<ClinicWithSpecilizedTypeDTO>> searchDoctors2(@PathVariable String date, @PathVariable String type) {
        return this.clinicService.searchClinics2(date, type);
    }
}
