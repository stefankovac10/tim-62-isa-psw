package com.ProjectCC.dero.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@SuperBuilder
@Getter
@Setter
public class ClinicAdministratorDTO extends UserDTO {
    private Long id;
    private String clinic;

//    @Autowired
//    public ClinicAdministratorDTO() {
//        super();
//    }
//
//    @Autowired
//    public ClinicAdministratorDTO(String firstName, String lastName, String jmbg, String password, String email, String address, String city, String country, String telephone, String clinic) {
//        super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
//        this.clinic = clinic;
//    }
}
