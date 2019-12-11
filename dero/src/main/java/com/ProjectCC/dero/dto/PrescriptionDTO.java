package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {
    private Long id;
    private Boolean certified;
    private List<MedicationDTO> medication;
    public ExaminationDTO examination;
    private DoctorDTO doctor;
    private NurseDTO nurse;

    /*
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

        Doctor doc = prescription.getDoctor();
        this.doctor = DoctorDTO.builder()
                .firstName(doc.getFirstName())
                .lastName(doc.getLastName())
                .address(doc.getAddress())
                .city(doc.getCity())    
                .country(doc.getCountry())
                .email(doc.getEmail())
                .jmbg(doc.getJmbg())
                .telephone(doc.getTelephone())
                .id(doc.getId())
                .build();
        Nurse nurse = prescription.getNurse();
        this.nurse = NurseDTO.builder()
                .firstName(nurse.getFirstName())
                .lastName(nurse.getLastName())
                .address(nurse.getAddress())
                .city(nurse.getCity())
                .country(nurse.getCountry())
                .email(nurse.getEmail())
                .jmbg(nurse.getJmbg())
                .telephone(nurse.getTelephone())
                .id(nurse.getId())
                .build();
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

     */
}
