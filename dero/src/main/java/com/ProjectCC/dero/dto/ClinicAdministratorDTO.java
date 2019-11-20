package com.ProjectCC.dero.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class ClinicAdministratorDTO extends UserDTO {
    private Long id;

    @Autowired
    public ClinicAdministratorDTO() {
        super();
    }

    @Autowired
    public ClinicAdministratorDTO(String firstName, String lastName, String jmbg, String password, String email, String address, String city, String country, String telephone) {
        super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
    }
}
