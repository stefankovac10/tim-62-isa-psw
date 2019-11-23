package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.service.PatientService;
import com.ProjectCC.dero.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/patient")
public class PatientController {

    private PatientService patientService;
    private RegistrationRequestService registrationRequestService;

    @Autowired
    public PatientController(PatientService patientService,RegistrationRequestService registrationRequestService) {
        this.patientService = patientService;
        this.registrationRequestService = registrationRequestService;
    }


    @GetMapping(value = "add/{id}")
    public ResponseEntity<Void> save(@PathVariable Long id){
        RegistrationRequest registrationRequest = registrationRequestService.findById(id);
        registrationRequestService.remove(id);

        Patient patient = new Patient();
        patient.setFirstName(registrationRequest.getFirstName());
        patient.setLastName(registrationRequest.getLastName());
        patient.setAddress(registrationRequest.getAddress());
        patient.setCity(registrationRequest.getCity());
        patient.setCountry(registrationRequest.getCountry());
        patient.setEmail(registrationRequest.getEmail());
        patient.setJmbg(registrationRequest.getJmbg());
        patient.setPassword(registrationRequest.getPassword());
        patient.setTelephone(registrationRequest.getTelephone());

        System.out.println(patient.getId());
        System.out.println(patient);


        patient = patientService.save(patient);

        if(patient != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> findAll(){
        List<Patient> patients = patientService.findAll();

        List<UserDTO> patientsDTOS = new ArrayList<>();
        for(Patient p : patients){
            patientsDTOS.add(new UserDTO(p));
        }

        return new ResponseEntity<>(patientsDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getPatient(@PathVariable Long id) {

        Patient patient = patientService.findById(id);

        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(patient), HttpStatus.OK);
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
}
