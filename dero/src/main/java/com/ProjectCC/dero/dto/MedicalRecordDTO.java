package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.MedicalRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MedicalRecordDTO {
    private Long id;
    private int height;
    private int width;
    private String bloodType;
    private String diopter;
    private UserDTO patient;
    private Set<ExaminationDTO> examinations;

    public MedicalRecordDTO(Long id, int height, int width, String bloodType, String diopter) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.bloodType = bloodType;
        this.diopter = diopter;
        this.patient = null;
        this.examinations = null;
    }

    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(MedicalRecord medicalRecord){
        this.height = medicalRecord.getHeight();
        this.width = medicalRecord.getWeight();
        this.id = medicalRecord.getId();
        this.bloodType = medicalRecord.getBloodType();
        this.diopter = medicalRecord.getDiopter();
        this.patient = new UserDTO(medicalRecord.getPatient());
        for(Examination exam: medicalRecord.getExaminations()){
            this.examinations.add(new ExaminationDTO(exam));
        }
    }
}
