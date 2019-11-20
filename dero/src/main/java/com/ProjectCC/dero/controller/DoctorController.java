package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users/doc")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<DoctorDTO> saveDoctor(DoctorDTO doctor) {

        Doctor doc = new Doctor(doctor.getFirstName(), doctor.getLastName(), doctor.getJmbg(),
                doctor.getPassword(), doctor.getEmail(), doctor.getAddress(), doctor.getCity(),
                doctor.getCountry(), doctor.getTelephone());

        doc = doctorService.save(doc);
        return new ResponseEntity<>(new DoctorDTO(doc), HttpStatus.CREATED);
    }
}
