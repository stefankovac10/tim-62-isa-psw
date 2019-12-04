package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ExaminationDTO;
import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.Prescription;
import com.ProjectCC.dero.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationService {

    private ExaminationRepository examinationRepository;
    private DiagnosisRepository diagnosisRepository;
    private MedicationRepository medicationRepository;
    private DoctorRepository doctorRepository;
    private NurseRepository nurseRepository;

    @Autowired
    public ExaminationService(ExaminationRepository examinationRepository, DiagnosisRepository diagnosisRepository, MedicationRepository medicationRepository, DoctorRepository doctorRepository, NurseRepository nurseRepository) {
        this.examinationRepository = examinationRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.medicationRepository = medicationRepository;
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
    }

    public void save(ExaminationDTO examinationDTO) {
        Examination examination = new Examination();
        examination.setReport(examinationDTO.getReport());
        examination.setDiagnosis(diagnosisRepository.getByName(examinationDTO.getDiagnosis()));
        Prescription prescription = new Prescription();
        prescription.setCertified(false);
        prescription.setDoctor(doctorRepository.getOne((long) 6));
        prescription.setNurse(nurseRepository.getOne((long) 7));
        prescription.setMedication(medicationRepository.getByName(examinationDTO.getMedicine()));
        examination.setPrescription(prescription);

        examinationRepository.save(examination);
    }
}
