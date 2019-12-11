package com.ProjectCC.dero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO extends UserDTO {
    private Long id;
    public MedicalRecordDTO medicalRecord;
    public List<ExaminationDTO> examinations;
    public List<OperationDTO> operations;
}
