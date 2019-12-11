package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.asm.Advice;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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
}
