package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.util.JsonJodaDateTimeSerializer;
import com.ProjectCC.dero.util.JsonJodaLocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.joda.time.LocalDate;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestDTO {
    private Long id;
    @JsonSerialize(using = JsonJodaLocalDateSerializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = JsonJodaLocalDateSerializer.class)
    private LocalDate endDate;
//    private String startDate;
//    private String endDate;
    private boolean accepted;
    public MedicalStaffDTO medicalStaff;
    private ClinicDTO clinic;
//
//    public VacationRequestDTO() {}
//
//    public VacationRequestDTO(String startDate, String endDate) {
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }
}
