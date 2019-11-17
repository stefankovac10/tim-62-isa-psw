package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.User;
import lombok.Getter;
import net.bytebuddy.asm.Advice;

@Getter
public class UserDTO {
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

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getJmbg(),
                user.getPassword(), user.getEmail(), user.getAddress(), user.getCity(),
                user.getCountry(), user.getTelephone());
    }

    public UserDTO(String firstName, String lastName, String jmbg,
                   String password, String email, String address, String city,
                   String country, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbg = jmbg;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.telephone = telephone;
    }

    public UserDTO(Long id, String firstName, String lastName, String jmbg,
                   String password, String email, String address, String city,
                   String country, String telephone) {
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
    }
}
