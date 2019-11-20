package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.VacationRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public class MedicalStaffDTO extends UserDTO{

    @Getter
    public ClinicDTO clinic;
//    @Getter
//    public String clinicString;
    private VacationRequest vacationRequest;

    @Autowired
    public MedicalStaffDTO() {
    }

    @Autowired
    public MedicalStaffDTO(String firstName, String lastName, String jmbg,
                           String password, String email, String address, String city,
                           String country, String telephone) {
        super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
    }

    @Autowired
    public MedicalStaffDTO(Long id, String firstName, String lastName, String jmbg,
                           String password, String email, String address, String city,
                           String country, String telephone, ClinicDTO clinic) {
        super(id, firstName, lastName, jmbg, password, email, address, city, country, telephone);
        this.clinic = clinic;
    }
}
