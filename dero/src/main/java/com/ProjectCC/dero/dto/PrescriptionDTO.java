package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.Medication;
import com.ProjectCC.dero.model.Prescription;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PrescriptionDTO {
    private Long id;
    private Boolean certified;
    private Set<MedicationDTO> medications;
    private DoctorDTO doctor;
    private NurseDTO nurse;


    public PrescriptionDTO(Prescription prescription) {
        this.id = prescription.getId();
        this.certified = prescription.getCertified();
        //this.doctor = new DoctorDTO(prescription.getDoctor());
        //this.nurse = new NurseDTO(prescription.getNurse());
        Set<MedicationDTO> medications = new HashSet<>();
        for (Medication med: prescription.getMedication()){
            medications.add(new MedicationDTO(med));
        }
        this.medications = medications;

    }

    public PrescriptionDTO() {

    }

    public PrescriptionDTO(Long id, boolean certified, Set<MedicationDTO> medications, DoctorDTO doctor, NurseDTO nurse) {
        this.id = id;
        this.certified = certified;
        this.medications = medications;
        this.doctor = doctor;
        this.nurse = nurse;
    }
}
