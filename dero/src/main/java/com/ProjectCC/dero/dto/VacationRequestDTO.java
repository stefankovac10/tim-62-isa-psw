package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.ClinicAdministrator;
import com.ProjectCC.dero.model.MedicalStaff;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private boolean accepted;
    public MedicalStaff medicalStaff;
    private ClinicAdministrator administrator;
//
//    public VacationRequestDTO() {}
//
//    public VacationRequestDTO(String startDate, String endDate) {
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }
}
