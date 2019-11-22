package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
    private double income;

    public ClinicDTO() {
    }

    public ClinicDTO(Long id, String name, String address, String description, double income) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.income = income;
    }

    public ClinicDTO(Long id, String name, String address, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public ClinicDTO(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public ClinicDTO(Clinic clinic){
        this(clinic.getId(),clinic.getName(),clinic.getAddress(),clinic.getDescription(), clinic.getIncome());
    }
}
