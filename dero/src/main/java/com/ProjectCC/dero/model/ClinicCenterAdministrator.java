package com.ProjectCC.dero.model;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "clinicCenter_id", nullable = false)
    public ClinicCenter clinicCenter;

    public ClinicCenterAdministrator(String firstName, String lastName, String jmbg, String password, String email, String address, String city, String country, String telephone, ClinicCenter clinicCenter) {
        super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
        this.clinicCenter = clinicCenter;
    }

    public ClinicCenterAdministrator() {
        super();
    }
}