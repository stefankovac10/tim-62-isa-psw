package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.util.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationRoomRequestDTO {
    private Long requestId;
    private OperationRoomDTO room;
    private List<DoctorDTO> doctors;
}
