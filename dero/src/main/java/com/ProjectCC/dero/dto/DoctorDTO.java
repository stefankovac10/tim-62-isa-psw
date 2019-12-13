package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO extends MedicalStaffDTO {
    private Long id;
    private Double grade;
    public List<ExaminationDTO> examinations;
    public Set<OperationDTO> operations;
    private TypeOfExaminationDTO specialisedType;

}
