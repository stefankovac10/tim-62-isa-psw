package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.MedicalStaff;
import com.ProjectCC.dero.model.Room;
import com.ProjectCC.dero.dto.RoomDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
    private Double income;
    private Double grade;
    private int pages;

    private List<MedicalStaffDTO> medicalStaff;
    private List<RoomDTO> rooms;
    private List<ExaminationDTO> examinations;
//    public ClinicDTO() {
//    }
//
//    public ClinicDTO(Long id, String name, String address, String description, double income) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.description = description;
//        this.income = income;
//    }
//
//    public ClinicDTO(Long id, String name, String address, String description) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.description = description;
//    }
//
//    public ClinicDTO(String name, String address, String description) {
//        this.name = name;
//        this.address = address;
//        this.description = description;
//    }
//
//    public ClinicDTO(Clinic clinic){
//        this(clinic.getId(),clinic.getName(),clinic.getAddress(),clinic.getDescription(), clinic.getIncome());
//    }
}
