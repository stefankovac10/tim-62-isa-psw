package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.util.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.joda.time.DateTime;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestDTO {
    private Long id;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime startDate;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime endDate;
    private boolean accepted;
    public MedicalStaffDTO medicalStaff;
    private ClinicDTO clinic;

}
