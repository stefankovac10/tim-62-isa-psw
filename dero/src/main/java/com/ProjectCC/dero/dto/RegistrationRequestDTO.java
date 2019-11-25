package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.RegistrationRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(String firstName, String lastName, String jmbg, String password, String email, String address, String city, String country, String telephone, boolean verified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
        this.verified = verified;
    }

    public RegistrationRequestDTO(Long id, String firstName, String lastName, String jmbg, String password, String email, String address, String city, String country, String telephone, boolean verified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
        this.verified = verified;
    }

    public RegistrationRequestDTO(RegistrationRequest registrationRequest){
        this(registrationRequest.getId(),registrationRequest.getFirstName(),registrationRequest.getLastName(),
               registrationRequest.getJmbg(), registrationRequest.getPassword(), registrationRequest.getEmail(),
                registrationRequest.getAddress(), registrationRequest.getCountry(), registrationRequest.getCountry(),
                registrationRequest.getTelephone(), registrationRequest.isVerified());
    }
}
