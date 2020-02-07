package com.ProjectCC.dero.dto;
import com.ProjectCC.dero.util.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {
    private Long id;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime date;
    // kako se serijalizuje
    private Duration duration;
    public MedicalRecordDTO medicalRecord;
    public OperationRoomDTO operationRoom;
    public List<DoctorDTO> doctors;
    public PatientDTO patient;
}
