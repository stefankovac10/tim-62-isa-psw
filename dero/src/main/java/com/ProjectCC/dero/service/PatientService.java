package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;

    @Autowired
    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    public Patient save(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));

        List<Authority> authorities = authorityService.findByName("ROLE_PATIENT");
        patient.setAuthorities(authorities);

        return patientRepository.save(patient);
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

}