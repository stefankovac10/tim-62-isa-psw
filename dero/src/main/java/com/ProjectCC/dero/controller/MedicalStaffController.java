package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.MedicalStaffDTO;
import com.ProjectCC.dero.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/medicalStaff")
public class MedicalStaffController {

    private MedicalStaffService medicalStaffService;

    @Autowired
    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    @GetMapping(value = "/mail/{email:.+}")
    public ResponseEntity<MedicalStaffDTO> getMedicalStaff(@PathVariable String email) {
        return this.medicalStaffService.getByEmail(email);
    }
}
