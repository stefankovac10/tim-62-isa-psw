package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String jmbg;
    private String password;
    private String email;
    private String address;
    private String city;
    private String country;
    private String telephone;
    private boolean verified;
    private int pages;

}
