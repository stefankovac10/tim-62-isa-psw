package com.ProjectCC.dero.service;


import com.ProjectCC.dero.model.Medication;
import com.ProjectCC.dero.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    private MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }

    public Medication save(Medication medication){
        return medicationRepository.save(medication);
    }

    public void remove(Long id){
        medicationRepository.deleteById(id);
    }

    public Medication findOne(Long id) {
        return medicationRepository.findById(id).orElseGet(null);
    }

    public void update(Medication medication){
        medicationRepository.update(medication.getName(), medication.getCode(), medication.getDescription(), medication.getId());
    }
}
