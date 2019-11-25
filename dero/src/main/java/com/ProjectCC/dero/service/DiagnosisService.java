package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.Diagnosis;
import com.ProjectCC.dero.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    private DiagnosisRepository diagnosisRepository;

    @Autowired
    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public List<Diagnosis> findAll() {
        return diagnosisRepository.findAll();
    }

    public Diagnosis save(Diagnosis diagnosis) {

        Diagnosis diagnosis_find = diagnosisRepository.findByName(diagnosis.getName());
        if (diagnosis_find == null) {
            return diagnosisRepository.save(diagnosis);
        }
        return null;
    }

    public void remove(Long id) {
        diagnosisRepository.deleteById(id);
    }

    public Diagnosis findOne(Long id) {
        return diagnosisRepository.findById(id).orElseGet(null);
    }

    public void update(Diagnosis diagnosis) {
        Diagnosis diagnosis_find = diagnosisRepository.findByName(diagnosis.getName());
        if (diagnosis_find == null || diagnosis_find.getId() == diagnosis.getId()) {
            diagnosisRepository.update(diagnosis.getName(), diagnosis.getCode(), diagnosis.getDescription(), diagnosis.getId());
        }
    }
}
