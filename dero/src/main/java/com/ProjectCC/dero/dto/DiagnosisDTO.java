package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Diagnosis;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnosisDTO {
    private Long id;
    private String name;
    private String code;
    private String description;

    public DiagnosisDTO() {
    }

    public DiagnosisDTO(Long id, String name, String description, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public DiagnosisDTO(String name, String description, String code) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public DiagnosisDTO(Diagnosis diagnosis){
        this(diagnosis.getId(),diagnosis.getName(), diagnosis.getDescription(),diagnosis.getCode());
    }

}
