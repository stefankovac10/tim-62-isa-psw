package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.MedicalRecordDTO;
import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.model.Operation;
import com.ProjectCC.dero.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/medicalrecord")
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize(" hasRole('ROLE_DOCTOR') || hasRole('ROLE_NURSE') || hasRole('ROLE_PATIENT')")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecord(@PathVariable Long id) {
        return new ResponseEntity<>(medicalRecordService.findOne(id), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    @PreAuthorize(" hasRole('ROLE_DOCTOR') || hasRole('ROLE_NURSE')")
    public ResponseEntity<MedicalRecordDTO> update(@RequestBody MedicalRecordDTO medicalRecordDTO){
        return new ResponseEntity<>(medicalRecordService.edit(medicalRecordDTO), HttpStatus.OK);
    }

}
