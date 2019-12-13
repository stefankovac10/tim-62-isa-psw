package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.PatientDTO;
import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.service.MedicalRecordService;
import com.ProjectCC.dero.service.PatientService;
import com.ProjectCC.dero.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/patient")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "add/{id}")
    public ResponseEntity<Void> save(@PathVariable Long id) throws URISyntaxException {
        return patientService.save(id);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> findAll(){
        List<Patient> patients = patientService.findAll();

        List<UserDTO> patientsDTOS = new ArrayList<>();
        for(Patient p : patients){
            patientsDTOS.add(UserDTO.builder()
                    .firstName(p.getFirstName())
                    .lastName(p.getLastName())
                    .address(p.getAddress())
                    .city(p.getCity())
                    .country(p.getCountry())
                    .email(p.getEmail())
                    .jmbg(p.getJmbg())
                    .telephone(p.getTelephone())
                    .id(p.getId())
                    .build());
        }

        return new ResponseEntity<>(patientsDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getPatient(@PathVariable Long id) {

        Patient patient = patientService.findById(id);

        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserDTO.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .address(patient.getAddress())
                .city(patient.getCity())
                .country(patient.getCountry())
                .email(patient.getEmail())
                .jmbg(patient.getJmbg())
                .telephone(patient.getTelephone())
                .id(patient.getId())
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {

        Patient patient  = patientService.findOne(id);

        if (patient != null) {
            patientService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/search/{firstName}/{lastName}/{jmbg}")
    public ResponseEntity<List<PatientDTO>> searchPatients(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String jmbg) {
        return this.patientService.search(firstName, lastName, jmbg);
    }
}
