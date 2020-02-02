package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Diagnosis;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisDTO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private int pages;

    public DiagnosisDTO(Diagnosis diagnosis){
        this(diagnosis.getId(),diagnosis.getName(), diagnosis.getDescription(),diagnosis.getCode(), 0);
    }

}
