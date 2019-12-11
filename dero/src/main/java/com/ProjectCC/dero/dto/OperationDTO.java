package com.ProjectCC.dero.dto;
import com.ProjectCC.dero.util.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {
    private Long id;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime date;
    // kako se serijalizuje
    private Period duration;
    public MedicalRecordDTO medicalRecord;
    public OperationRoomDTO operationRoom;
    public List<DoctorDTO> doctors;
    public PatientDTO patient;
}
