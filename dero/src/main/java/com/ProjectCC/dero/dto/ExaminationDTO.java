package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Diagnosis;
import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.Patient;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationDTO {
    private Long id;
    private String date;
    private String type;
    private String duration;
    private String price;
    private String discount;
    private String report;
    private PrescriptionDTO prescription;
    private DiagnosisDTO diagnosis;
    private NurseDTO nurse;
    private DoctorDTO doctor;
    private UserDTO patient;
    private MedicalRecordDTO medicalRecord;

    /*
    public ExaminationDTO() {

    }

    public ExaminationDTO(Examination examination) {
        this.id = examination.getId();
        this.date = examination.getDate();
        this.type = examination.getType();
        this.duration = examination.getDuration();
        this.price = examination.getPrice();
        this.discount = examination.getDiscount();
        this.report = examination.getReport();
        this.prescription = new PrescriptionDTO(examination.getPrescription());
        this.diagnosis = null;
        this.nurse = new NurseDTO(examination.getNurse());
        this.doctor = new DoctorDTO(examination.getDoctor());
        this.patient = new UserDTO(examination.getPatient());
        this.medicalRecord = new MedicalRecordDTO(examination.getMedicalRecord());
    }

    public ExaminationDTO(Long id, String date, String type, String duration, String price, String discount, String report, PrescriptionDTO prescription, DiagnosisDTO diagnosis, NurseDTO nurse, DoctorDTO doctor, UserDTO patient, MedicalRecordDTO medicalRecord) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.duration = duration;
        this.price = price;
        this.discount = discount;
        this.report = report;
        this.prescription = prescription;
        this.diagnosis = diagnosis;
        this.nurse = nurse;
        this.doctor = doctor;
        this.patient = patient;
        this.medicalRecord = medicalRecord;
    }

    */

}
