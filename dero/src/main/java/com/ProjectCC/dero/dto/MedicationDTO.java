package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Medication;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationDTO {
    private Long id;
    private String name;
    private String description;
    private String code;

    public MedicationDTO() {
    }

    public MedicationDTO(Long id, String name, String description, String code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public MedicationDTO(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public MedicationDTO(Medication medication) {
        this(medication.getId(),medication.getName(), medication.getDescription(), medication.getCode());
    }


}
