package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.model.Operation;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class PatientDTO {
    private Long id;
    public MedicalRecordDTO medicalRecord;
    public List<ExaminationDTO> examinations;
    public List<OperationDTO> operations;
}
