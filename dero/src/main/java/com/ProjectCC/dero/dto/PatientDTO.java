package com.ProjectCC.dero.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO extends UserDTO {
    private Long id;
    public MedicalRecordDTO medicalRecord;
    public List<ExaminationDTO> examinations;
    public List<OperationDTO> operations;
}
