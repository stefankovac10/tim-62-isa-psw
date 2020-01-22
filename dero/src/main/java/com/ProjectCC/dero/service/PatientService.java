package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.PatientDTO;
import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;

    @Autowired
    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder,
                AuthorityService authorityService, RegistrationRequestService registrationRequestService,
                          MedicalRecordService medicalRecordService, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
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
        patient.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        List<Authority> authorities = authorityService.findByName("ROLE_PATIENT");
        patient.setAuthorities(authorities);
        patient = patientRepository.save(patient);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("http://localhost:8081/registerPage"));

        if(patient != null)
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public List<PatientDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Patient> patients = patientRepository.findAll(pageable);

        List<PatientDTO> patientsDTOS = new ArrayList<>();
        for(Patient p : patients.getContent()){
            patientsDTOS.add(PatientDTO.builder()
                    .firstName(p.getFirstName())
                    .lastName(p.getLastName())
                    .address(p.getAddress())
                    .city(p.getCity())
                    .country(p.getCountry())
                    .email(p.getEmail())
                    .jmbg(p.getJmbg())
                    .telephone(p.getTelephone())
                    .id(p.getId())
                    .pages(patients.getTotalPages())
                    .build());
        }
        return patientsDTOS;
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

    public ResponseEntity<List<PatientDTO>> search(String firstName, String lastName, String jmbg, int page) {
        String fn = "";
        String ln = "";
        String j =  "";
        if (!firstName.equals("%")) {
            fn = "%" + firstName + "%";
        } else fn = firstName;
        if (!lastName.equals("%")) {
            ln = "%" + lastName + "%";
        } else ln = lastName;
        if (!jmbg.equals("%")) {
            j = "%" + jmbg + "%";
        } else j = jmbg;
        Pageable pageable = PageRequest.of(page, 10);
        Page<Patient> patients = this.patientRepository.search(fn, ln, j, pageable);
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient p : patients) {
            patientDTOS.add(PatientDTO.builder()
                .id(p.getId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .jmbg(p.getJmbg())
                .email(p.getEmail())
                .telephone(p.getTelephone())
                .pages(patients.getTotalPages())
                .build());
        }
        return new ResponseEntity<>(patientDTOS, HttpStatus.OK);
    }
}