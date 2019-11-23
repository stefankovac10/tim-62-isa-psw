package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient save(Patient patient) {
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