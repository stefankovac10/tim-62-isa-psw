package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.MedicalRecord;
import com.ProjectCC.dero.model.VacationRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalStaffDTO extends UserDTO{
    private Long id;
    public ClinicDTO clinic;
    private VacationRequestDTO vacationRequest;
    public List<MedicalRecord> medicalRecords;

}
