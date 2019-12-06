package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.TypeOfExamination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfExaminationDTO {
    private Long id;
    private String name;
    private String description;
    private List<DoctorDTO> specialisedDoctors;

//    @Autowired
//    public TypeOfExaminationDTO(String name, String description) {
//        this.name = name;
//        this.description = description;
//    }
//
//    @Autowired
//    public TypeOfExaminationDTO(TypeOfExamination type) {
//        this.id = type.getId();
//        this.name = type.getName();
//        this.description = type.getDescription();
//    }
}
