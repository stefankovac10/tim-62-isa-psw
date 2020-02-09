package com.ProjectCC.dero.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicWithSpecilizedTypeDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
    private Long specializedTypeID;

    private List<MedicalStaffDTO> medicalStaff;
    private List<RoomDTO> rooms;
    private List<ExaminationDTO> examinations;
}
