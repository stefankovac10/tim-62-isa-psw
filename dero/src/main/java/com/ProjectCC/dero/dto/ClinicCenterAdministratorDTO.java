package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.ClinicCenterAdministrator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class ClinicCenterAdministratorDTO extends  UserDTO{
    private Long id;
    private boolean logFirstTime;

    @Autowired
    public ClinicCenterAdministratorDTO() {
        super();
    }

    @Autowired
    public ClinicCenterAdministratorDTO(String firstName, String lastName, String jmbg, String password, String email, String address, String city, String country, String telephone, Long id, boolean logFirstTime) {
        super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
        this.id = id;
        this.logFirstTime = logFirstTime;
    }

    @Autowired
    public ClinicCenterAdministratorDTO(ClinicCenterAdministrator ccadmin) {
        super(ccadmin.getFirstName(), ccadmin.getLastName(), ccadmin.getJmbg(), ccadmin.getPassword(), ccadmin.getEmail(), ccadmin.getAddress(), ccadmin.getCity(), ccadmin.getCountry(), ccadmin.getTelephone());
        this.id = ccadmin.getId();
        this.logFirstTime = ccadmin.isLogFirstTime();
    }

}
