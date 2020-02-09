package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.service.ClinicService;
import com.ProjectCC.dero.service.DoctorService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:8081")
@RequestMapping(value = "/api/users/doc")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService, ClinicService clinicService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/{type}", consumes = "application/json")
    @PreAuthorize(" hasRole('ROLE_CADMIN')")
    public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody DoctorDTO doctorDTO, @PathVariable String type) {
        return doctorService.save(doctorDTO, type);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize(" hasRole('ROLE_CADMIN')")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        return this.doctorService.delete(id);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return this.doctorService.findAll();
    }

    @GetMapping(value = "/avaliable/{id}/{nextAvailable}")
    @PreAuthorize(" hasRole('ROLE_CADMIN') || hasRole('ROLE_PATIENT')")
    public ResponseEntity<List<DoctorDTO>> getAvaliableDoctors(@PathVariable Long id, @PathVariable String nextAvailable) {
        return this.doctorService.findAvaliable(id, nextAvailable);
    }

    @GetMapping(value = "/search/{firstName}/{lastName}/{email}/{city}/{country}/{clinic}")
    @PreAuthorize(" hasRole('ROLE_PATIENT')")
    public ResponseEntity<List<DoctorDTO>> searchDoctors(@PathVariable String firstName,
                                                         @PathVariable String lastName,
                                                         @PathVariable String email,
                                                         @PathVariable String city,
                                                         @PathVariable String country,
                                                         @PathVariable String clinic) {
        return this.doctorService.searchDoctors(firstName, lastName, email, city, country, clinic);
    }

    @GetMapping(value = "/{clinicID}/{typeID}/{date}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsByClinicAndTypeAndDate(@PathVariable Long clinicID,
                                                                         @PathVariable Long typeID,
                                                                         @PathVariable String date) {
        return doctorService.getDoctorsByClinicAndTypeAndDate(clinicID, typeID, date);
    }

    @GetMapping(value = "clinic/{id}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsFromClinic(@PathVariable Long id) {
        return this.doctorService.getDoctorsFromClinic(id);
    }
}
