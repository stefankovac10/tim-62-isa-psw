package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.service.ClinicService;
import com.ProjectCC.dero.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:8081")
@RequestMapping(value = "/api/users/doc")
public class DoctorController {

    private DoctorService doctorService;
    private ClinicService clinicService;

    @Autowired
    public DoctorController(DoctorService doctorService, ClinicService clinicService) {
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody DoctorDTO doctor) {

        Doctor doc = new Doctor(doctor.getFirstName(), doctor.getLastName(), doctor.getJmbg(),
                doctor.getPassword(), doctor.getEmail(), doctor.getAddress(), doctor.getCity(),
                doctor.getCountry(), doctor.getTelephone());
        Clinic clinic = clinicService.findOne((long) 1);
        doc.setClinic(clinic);

        doc = doctorService.save(doc);

        return new ResponseEntity<>(new DoctorDTO(doc), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable  Long id) {
        doctorService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return this.doctorService.findAll();
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<DoctorDTO>> pronadjiDoktorePoImenuMejluGraduDrzavi(@RequestParam String firstName, String lastName, String email, String city, String country) {
        return this.doctorService.pronadjiPoImenuMejluGraduDrzavi(firstName, lastName, email, city, country);
    }
}
