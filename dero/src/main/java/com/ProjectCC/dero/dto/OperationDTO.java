package com.ProjectCC.dero.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {
    private Long id;
    private String date;
    private String duration;
    public MedicalRecordDTO medicalRecord;
    public OperationRoomDTO operationRoom;
    public List<DoctorDTO> doctors;
    public PatientDTO patient;
}
