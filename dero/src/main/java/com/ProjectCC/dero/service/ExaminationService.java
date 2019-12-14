package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ExaminationDTO;
import com.ProjectCC.dero.dto.MedicationDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminationService {

    private ExaminationRepository examinationRepository;
    private DiagnosisRepository diagnosisRepository;
    private ModelMapper modelMapper;
    private MedicationRepository medicationRepository;
    private DoctorRepository doctorRepository;
    private NurseRepository nurseRepository;
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public ExaminationService(MedicalRecordRepository medicalRecordRepository, ModelMapper modelMapper,ExaminationRepository examinationRepository, DiagnosisRepository diagnosisRepository, MedicationRepository medicationRepository, DoctorRepository doctorRepository, NurseRepository nurseRepository) {
        this.examinationRepository = examinationRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.medicationRepository = medicationRepository;
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
        this.modelMapper = modelMapper;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public void save(ExaminationDTO examinationDTO) {

        Set<Medication> medications = new HashSet<>();
        for(MedicationDTO med: examinationDTO.getPrescription().getMedication()){
            medications.add(modelMapper.map(med, Medication.class));
        }

        Prescription prescription = Prescription.builder()
                                    .medication(medications)
                                    .certified(false)
                                    .build();

        MedicalRecord medicalRecord = medicalRecordRepository.getOne((long)1);

        Examination examination = Examination.builder()
                                    .report(examinationDTO.getReport())
                                    .prescription(prescription)
                                    .diagnosis(modelMapper.map(examinationDTO.getDiagnosis(), Diagnosis.class))
                                    .medicalRecord(medicalRecord)
                                    .build();

        examinationRepository.save(examination);
    }

    public void addExaminationReport(ExaminationDTO examinationDTO){
        Set<Medication> medications = new HashSet<>();
        for(MedicationDTO med: examinationDTO.getPrescription().getMedication()){
            medications.add(modelMapper.map(med, Medication.class));
        }

        Prescription prescription = Prescription.builder()
                                .medication(medications)
                                .certified(false)
                                .build();

        Examination examination = examinationRepository.findById(examinationDTO.getId()).orElseGet(null);
        examination.setPrescription(prescription);
        examination.setDiagnosis(modelMapper.map(examinationDTO.getDiagnosis(), Diagnosis.class));
        examination.setReport(examinationDTO.getReport());

        examinationRepository.save(examination);
    }

    public ExaminationDTO edit(ExaminationDTO examinationDTO) {
        Examination examination = examinationRepository.findById(examinationDTO.getId()).orElseGet(null);
        examination.setReport(examinationDTO.getReport());

        Diagnosis diagnosis = diagnosisRepository.findById(examinationDTO.getDiagnosis().getId()).orElseGet(null);
        examination.setDiagnosis(diagnosis);

        examinationRepository.save(examination);

        return examinationDTO;
    }

    public ExaminationDTO getOne(Long id) {
        Examination examination = examinationRepository.getOne(id);
        ExaminationDTO examinationDTO= ExaminationDTO.builder()
                                       .id(examination.getId())
                                       .report(examination.getReport())
                                       .build();
        return examinationDTO;
    }
}
