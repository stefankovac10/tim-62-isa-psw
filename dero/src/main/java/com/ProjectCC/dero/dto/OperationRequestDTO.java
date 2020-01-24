package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.util.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.joda.time.DateTime;
import org.joda.time.Duration;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequestDTO {
    private Long id;
    private Long doctorId;
    private Long patientId;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime date;
    private Duration duration;
}
