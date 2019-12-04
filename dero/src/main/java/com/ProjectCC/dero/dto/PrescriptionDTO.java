package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PrescriptionDTO {
    private Long id;
    private boolean certified;
    private List<MedicationDTO> medications;
    private DoctorDTO doctor;
    private NurseDTO nurse;


    public PrescriptionDTO(Prescription prescription) {
        this.id = prescription.getId();
        this.certified = prescription.getCertified();
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


}
