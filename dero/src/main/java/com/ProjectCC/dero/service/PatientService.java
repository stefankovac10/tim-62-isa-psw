package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.PatientDTO;
import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
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