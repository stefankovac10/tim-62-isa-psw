package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.Medication;
import com.ProjectCC.dero.model.Prescription;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PrescriptionDTO {
    private Long id;
    private boolean certified;
    private Set<MedicationDTO> medications;
    private DoctorDTO doctor;
    private NurseDTO nurse;
    private ExaminationDTO examination;

    public PrescriptionDTO(Prescription prescription) {
        this.id = prescription.getId();
        this.certified = prescription.getCertified();
        this.doctor = new DoctorDTO(prescription.getDoctor());
        this.nurse = new NurseDTO(prescription.getNurse());
        for(Medication med : prescription.getMedication()){
            this.medications.add(new MedicationDTO(med));
        }
        this.examination = new ExaminationDTO(prescription.getExamination());
    }

    public PrescriptionDTO() {

    }


}