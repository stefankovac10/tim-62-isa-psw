package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.TypeOfExamination;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public class TypeOfExaminationDTO {
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private String description;

    @Autowired
    public TypeOfExaminationDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Autowired
    public TypeOfExaminationDTO(TypeOfExamination type) {
        this.id = type.getId();
        this.name = type.getName();
        this.description = type.getDescription();
    }
}
