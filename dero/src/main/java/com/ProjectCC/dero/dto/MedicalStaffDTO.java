package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.VacationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalStaffDTO extends UserDTO{

    @Getter
    public ClinicDTO clinic;

    private VacationRequestDTO vacationRequest;

}
