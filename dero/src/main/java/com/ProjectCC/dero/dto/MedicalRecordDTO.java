package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.model.Patient;
import lombok.*;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDTO {
    private Long id;
    private int height;
    private int weight;
    private String bloodType;
    private String diopter;
    private UserDTO patient;
    private List<ExaminationDTO> examinations;
    private List<OperationDTO> operations;
    private List<MedicalStaffDTO> medicalStaffs;

/*
    public MedicalRecordDTO(Long id, int height, int width, String bloodType, String diopter) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.bloodType = bloodType;
        this.diopter = diopter;
        this.patient = null;
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
    }
*/
//    public MedicalRecordDTO(Long id, int height, int width, String bloodType, String diopter) {
//        this.id = id;
//        this.height = height;
//        this.width = width;
//        this.bloodType = bloodType;
//        this.diopter = diopter;
//        this.patient = null;
//        this.examinations = null;
//    }
//
//    public MedicalRecordDTO() {
//    }
//
//    public MedicalRecordDTO(MedicalRecord medicalRecord){
//        Patient p = medicalRecord.getPatient();
//        this.height = medicalRecord.getHeight();
//        this.width = medicalRecord.getWeight();
//        this.id = medicalRecord.getId();
//        this.bloodType = medicalRecord.getBloodType();
//        this.diopter = medicalRecord.getDiopter();
//        this.patient = new UserDTO(Patient);
//        for(Examination exam: medicalRecord.getExaminations()){
//            this.examinations.add(new ExaminationDTO(exam));
//        }
//    }
}
