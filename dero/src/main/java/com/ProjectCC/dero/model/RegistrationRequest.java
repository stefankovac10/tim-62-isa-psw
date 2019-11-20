package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class RegistrationRequest extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;*/

    private boolean verified;

    public RegistrationRequest() {
        super();
    }

    public RegistrationRequest(String firstName, String lastName, String jmbg, String password, String email, String address, String city, String country, String telephone, boolean verified) {
        super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
        this.verified = verified;
    }

    public RegistrationRequest(User user, boolean verified) {
        super(user.getFirstName(),
                user.getLastName(),
                user.getJmbg(),
                user.getPassword(),
                user.getEmail(),
                user.getAddress(),
                user.getCity(),
                user.getCountry(),
                user.getTelephone());
        this.verified = verified;
    }
}
