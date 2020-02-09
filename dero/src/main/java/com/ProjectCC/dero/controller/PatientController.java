package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.service.ExaminationRequestService;
import com.ProjectCC.dero.service.MedicalRecordService;
import com.ProjectCC.dero.service.PatientService;
import com.ProjectCC.dero.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private ExaminationRequestService examinationRequestService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "add/{id}")
    public ResponseEntity<Void> save(@PathVariable Long id) throws URISyntaxException {
        return patientService.save(id);
    }

    @GetMapping(value = "examination/{id}")
    public ResponseEntity<List<ExaminationDTO>> getExamination(@PathVariable Long id){
        List<ExaminationDTO> examinationDTOS =  patientService.getExamination(id);

        if(examinationDTOS ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(examinationDTOS,HttpStatus.OK);
        }
    }

    @GetMapping(value = "/all/{page}")
    public ResponseEntity<List<PatientDTO>> findAll(@PathVariable int page){
        List<PatientDTO> patientDTOS = this.patientService.findAll(page);
        return new ResponseEntity<>(patientDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long id) {

        Patient patient = patientService.findById(id);

        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MedicalRecordDTO medicalRecordDTO = MedicalRecordDTO.builder()
                .weight(patient.getMedicalRecord().getWeight())
                .height(patient.getMedicalRecord().getHeight())
                .diopter(patient.getMedicalRecord().getDiopter())
                .bloodType(patient.getMedicalRecord().getBloodType())
                .id(patient.getMedicalRecord().getId())
                .build();
        return new ResponseEntity<>(PatientDTO.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .address(patient.getAddress())
                .city(patient.getCity())
                .country(patient.getCountry())
                .email(patient.getEmail())
                .jmbg(patient.getJmbg())
                .telephone(patient.getTelephone())
                .id(patient.getId())
                .medicalRecord(medicalRecordDTO)
                .build(), HttpStatus.OK);
    }

    @GetMapping(value = "get/{email:.+}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable String email) {
        PatientDTO patientDTO = patientService.findByEmail(email);
        if(patientDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(patientDTO, HttpStatus.OK);
        }
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

    @GetMapping(value = "/search/{firstName}/{lastName}/{jmbg}/{page}")
    public ResponseEntity<List<PatientDTO>> searchPatients(@PathVariable String firstName, @PathVariable String lastName,
                                                           @PathVariable String jmbg, @PathVariable int page) {
        return this.patientService.search(firstName, lastName, jmbg, page);
    }

    /*
    @PostMapping(value = "/{doctorID}/{email}/{time}/{duration}/{date}", consumes = "application/json")
    public ResponseEntity<ExaminationRequestDTO> saveExaminationRequest(@PathVariable Long doctorID,
                                                                        @PathVariable String email,
                                                                        @PathVariable String time,
                                                                        @PathVariable int duration,
                                                                        @PathVariable String date) {
        return this.examinationRequestService.saveExaminationRequest(doctorID, email, time, duration, date);
    }
     */

    @PostMapping(value = "/saveExaminationRequest", consumes = "application/json")
    public ResponseEntity<CustomExaminationRequestDTO> saveExaminationRequest(@RequestBody CustomExaminationRequestDTO customExaminationRequestDTO) {
        return this.examinationRequestService.saveExaminationRequest(customExaminationRequestDTO);
    }
}
