package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.PatientDTO;
import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private RegistrationRequestService registrationRequestService;
    private MedicalRecordService medicalRecordService;
    private ModelMapper modelMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, RegistrationRequestService registrationRequestService, MedicalRecordService medicalRecordService, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.registrationRequestService = registrationRequestService;
        this.medicalRecordService = medicalRecordService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<Void> save(Long id) throws URISyntaxException {
        RegistrationRequest registrationRequest = registrationRequestService.findById(id);
        registrationRequestService.remove(id);

        MedicalRecord medicalRecord = MedicalRecord.builder()
                                        .height(0)
                                        .weight(0)
                                        .bloodType("")
                                        .diopter("")
                                        .build();
        medicalRecord = medicalRecordService.save(medicalRecord);

        Patient patient = Patient.builder()
                            .firstName(registrationRequest.getFirstName())
                            .lastName(registrationRequest.getLastName())
                            .address(registrationRequest.getAddress())
                            .city(registrationRequest.getCity())
                            .country(registrationRequest.getCountry())
                            .email(registrationRequest.getEmail())
                            .jmbg(registrationRequest.getJmbg())
                            .password(registrationRequest.getPassword())
                            .telephone(registrationRequest.getTelephone())
                            .medicalRecord(medicalRecord)
                            .build();
        patient = patientRepository.save(patient);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("http://localhost:8081/login"));

        if(patient != null)
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseGet(null);
    }

    public void remove(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient findOne(Long id) {
        return patientRepository.findById(id).orElseGet(null);
    }

    public ResponseEntity<List<PatientDTO>> search(String firstName, String lastName, String jmbg) {
        String fn = "%" + firstName + "%";
        String ln = "%" + lastName + "%";
        String j = "%" + jmbg + "%";
        List<Patient> patients = this.patientRepository.search(fn, ln, j);
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient p : patients) {
            patientDTOS.add(PatientDTO.builder()
                .id(p.getId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .jmbg(p.getJmbg())
                .build());
        }
        return new ResponseEntity<>(patientDTOS, HttpStatus.OK);
    }
}