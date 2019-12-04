package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord save(MedicalRecord medicalRecord){
            return medicalRecordRepository.save(medicalRecord);
    }

    public void remove(Long id){
        medicalRecordRepository.deleteById(id);
    }

    public MedicalRecord findOne(Long id) {
        return medicalRecordRepository.findById(id).orElseGet(null);
    }

    public void update(MedicalRecord medicalRecord) {
            medicalRecordRepository.update(medicalRecord.getHeight(), medicalRecord.getWeight(),medicalRecord.getBloodType(), medicalRecord.getDiopter(), medicalRecord.getId());
    }

}
