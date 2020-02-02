package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.util.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationDTO {
    private Long id;
    private String report;
    private String medicine;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime date;
    private TypeOfExaminationDTO type;
    // kako se serijalizuje?
    private Duration duration; // more changes needed probably
    private String price;
    private String discount;
    public ExaminationRoomDTO examinationRoom;
    // public List<DiagnosisDTO> diagnosis;
    private DiagnosisDTO diagnosis;
    public PrescriptionDTO prescription;
    public NurseDTO nurse;
    public DoctorDTO doctor;
    public PatientDTO patient;
    public MedicalRecordDTO medicalRecord;
    public ClinicDTO clinic;

}
