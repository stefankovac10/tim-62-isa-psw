package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Patient;
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
public class ExaminationRequestDetailsDTO {
    private Long id;
    private DoctorDTO doctor;
    private PatientDTO patient;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime date;
    private Duration duration;
//    private AppointmentDTO appointmentDTO;
    private int pages;
}
