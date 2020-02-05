package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.util.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime startDate;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime endDate;
    private Long duration;
    private ClinicDTO clinic;
}
