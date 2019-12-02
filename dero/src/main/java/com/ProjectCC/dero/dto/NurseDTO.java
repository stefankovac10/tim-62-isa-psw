package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Nurse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class NurseDTO extends MedicalStaffDTO {
    private Long id;


    public NurseDTO(String firstName, String lastName, String jmbg, String password,
                     String email, String address, String city, String country,
                     String telephone) {
        super(firstName, lastName, jmbg, password, email, address, city, country,
                telephone);
    }

    @Autowired
    public NurseDTO(Long id, String firstName, String lastName, String jmbg, String password,
                     String email, String address, String city, String country,
                     String telephone, ClinicDTO clinic) {
        super(id, firstName, lastName, jmbg, password, email, address, city, country,
                telephone, clinic);
    }

    @Autowired
    public NurseDTO(Nurse nurse) {
        super(nurse.getId(), nurse.getFirstName(), nurse.getLastName(), nurse.getJmbg(),
                nurse.getPassword(), nurse.getEmail(), nurse.getAddress(),
                nurse.getCity(), nurse.getCountry(), nurse.getTelephone(), new ClinicDTO(nurse.getClinic()));
    }

    public NurseDTO() {

    }
}
