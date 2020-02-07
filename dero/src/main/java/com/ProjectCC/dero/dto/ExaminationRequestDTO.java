package com.ProjectCC.dero.dto;

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
public class ExaminationRequestDTO {
    private Long id;
    private Long doctorId;
    private Long patientId;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime date;
    private Long duration;
//    private AppointmentDTO appointmentDTO;
    private Long typeId;
    private Long clinicId;
}
