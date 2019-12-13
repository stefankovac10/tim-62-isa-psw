package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.Examination;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfExaminationDTO {
    private Long id;
    private String name;
    private String description;
    private List<DoctorDTO> specialisedDoctors;
//    private List<ExaminationDTO> examinations;

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
