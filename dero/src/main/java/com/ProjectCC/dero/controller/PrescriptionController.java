package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.dto.NurseDTO;
import com.ProjectCC.dero.dto.PrescriptionDTO;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.Nurse;
import com.ProjectCC.dero.model.Prescription;
import com.ProjectCC.dero.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/api/prescription")
public class PrescriptionController {

    private PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PrescriptionDTO>> getAllMedication() {
        return new ResponseEntity<>(prescriptionService.findAll(), HttpStatus.OK);
    }

    @PostMapping( consumes = "application/json")
    public ResponseEntity<Void> save(@RequestBody PrescriptionDTO prescriptionDTO){
        prescriptionService.addPrescription(prescriptionDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping(value = "/certify/{id}")
    public ResponseEntity<Void> certify(@PathVariable Long id){
        prescriptionService.certify(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
