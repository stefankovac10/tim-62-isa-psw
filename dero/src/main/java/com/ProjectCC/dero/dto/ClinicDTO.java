package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.MedicalStaff;
import com.ProjectCC.dero.model.Room;
import com.ProjectCC.dero.dto.RoomDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ClinicDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
    private double income;

    private List<MedicalStaffDTO> medicalStaff;
    private List<RoomDTO> rooms;

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
