package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.ClinicCenterAdministratorDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ClinicCenterAdministrator extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logFirstTime", nullable = false)
    private boolean logFirstTime;


    public ClinicCenterAdministrator(String firstName, String lastName, String jmbg, String password,
                                     String email, String address, String city, String country,
                                     String telephone, boolean logFirstTime) {
        super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
        this.logFirstTime = logFirstTime;
    }

    public ClinicCenterAdministrator() {
        super();
    }

    public ClinicCenterAdministrator(ClinicCenterAdministratorDTO ccadminDTO){
        super(ccadminDTO.getFirstName(), ccadminDTO.getLastName(),ccadminDTO.getJmbg(),ccadminDTO.getPassword(),ccadminDTO.getEmail(),ccadminDTO.getAddress(),ccadminDTO.getCity(),ccadminDTO.getCountry(),ccadminDTO.getTelephone());
    }
}