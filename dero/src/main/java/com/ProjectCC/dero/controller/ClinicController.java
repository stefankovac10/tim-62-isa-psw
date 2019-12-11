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

    private ModelMapper modelMapper;
    private ClinicService clinicService;

    @Autowired
    public ClinicController(ModelMapper modelMapper, ClinicService clinicService) {
        this.modelMapper = modelMapper;
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        return clinicService.findAll();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ClinicDTO> save(@RequestBody ClinicDTO clinicDTO){
            Clinic clinic = modelMapper.map(clinicDTO, Clinic.class);

            clinic = clinicService.save(clinic);
            if(clinic == null){
                return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
            }
            clinicDTO.setId(clinic.getId());
            return new ResponseEntity<>(clinicDTO, HttpStatus.CREATED);

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

        return this.clinicService.findById(id);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ClinicDTO> update(@RequestBody ClinicDTO clinicDTO){
        return new ResponseEntity<>(this.clinicService.update(clinicDTO), HttpStatus.OK);

    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<ClinicDTO>> pronadjiKlinikePoImenuAdresiOpisu(@RequestBody ClinicDTO clinicDTO) {
        return this.clinicService.pronadjiPoImenuAdresiOpisu(clinicDTO);
    }

    @GetMapping(value = "/businessReport/{id}")
    public ResponseEntity<ClinicDTO> getReport(@PathVariable Long id) {
        return this.clinicService.businessReport(id);
    }
}
