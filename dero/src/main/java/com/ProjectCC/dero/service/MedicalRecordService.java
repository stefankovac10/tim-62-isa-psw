package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.model.Diagnosis;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.repository.MedicalRecordRepository;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {

    private MedicalRecordRepository medicalRecordRepository;
    private ModelMapper modelMapper;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, ModelMapper modelMapper) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.modelMapper = modelMapper;
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

    public MedicalRecordDTO findOne(Long id) {
        MedicalRecord mr = medicalRecordRepository.getOne(id);
        List<ExaminationDTO> examinations = new ArrayList<>();

        for(Examination ex: mr.getExaminations()){
            DoctorDTO doctor = DoctorDTO.builder()
                                .firstName(ex.getDoctor().getFirstName())
                                .lastName(ex.getDoctor().getLastName())
                                .build();
            TypeOfExaminationDTO toe = TypeOfExaminationDTO.builder()
                                .name(ex.getType().getName())
                                .build();
            ExaminationDTO examination = ExaminationDTO.builder()
                                        .report(ex.getReport())
                                        .id(ex.getId())
                                        .diagnosis(new DiagnosisDTO(ex.getDiagnosis()))
                                        .type(toe)
                                        .doctor(doctor)
                                        .build();
            examinations.add(examination);
        }

        MedicalRecordDTO mrDTO = MedicalRecordDTO.builder()
                                .id(mr.getId())
                                .height(mr.getHeight())
                                .weight(mr.getWeight())
                                .diopter(mr.getDiopter())
                                .bloodType(mr.getBloodType())
                                .examinations(examinations)
                                .build();
        return mrDTO;
    }

    public void update(MedicalRecord medicalRecord) {
            medicalRecordRepository.update(medicalRecord.getHeight(), medicalRecord.getWeight(),medicalRecord.getBloodType(), medicalRecord.getDiopter(), medicalRecord.getId());
    }

    public MedicalRecord getOne(Long id) {

        return  medicalRecordRepository.getOne(id);
    }

    public MedicalRecordDTO edit(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordRepository.getOne(medicalRecordDTO.getId());
        medicalRecord.setBloodType(medicalRecordDTO.getBloodType());
        medicalRecord.setDiopter(medicalRecordDTO.getDiopter());
        medicalRecord.setHeight(medicalRecordDTO.getHeight());
        medicalRecord.setWeight(medicalRecordDTO.getWeight());

        medicalRecordRepository.save(medicalRecord);

        return medicalRecordDTO;
    }

}
