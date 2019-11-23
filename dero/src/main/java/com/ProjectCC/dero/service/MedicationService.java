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
        Medication medication_find = medicationRepository.findByName(medication.getName());
        if (medication_find == null) {
            return medicationRepository.save(medication);
        }
        return null;

    }

    public void remove(Long id){
        medicationRepository.deleteById(id);
    }

    public Medication findOne(Long id) {
        return medicationRepository.findById(id).orElseGet(null);
    }

    public void update(Medication medication) {
        Medication medication_find = medicationRepository.findByName(medication.getName());
        Medication medication_find2 = medicationRepository.findByCode(medication.getCode());

        if (medication_find== null || medication_find2 == null || medication_find.getId() == medication.getId()) {
            medicationRepository.update(medication.getName(), medication.getCode(), medication.getDescription(), medication.getId());
        }
    }
}
