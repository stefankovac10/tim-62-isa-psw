package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.VacationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalStaffDTO extends UserDTO{

    @Getter
    public ClinicDTO clinic;

    private VacationRequestDTO vacationRequest;

//    @Autowired
//    public MedicalStaffDTO() {
//    }
//
//    @Autowired
//    public MedicalStaffDTO(String firstName, String lastName, String jmbg,
//                           String password, String email, String address, String city,
//                           String country, String telephone) {
//        super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
//    }
//
//    @Autowired
//    public MedicalStaffDTO(Long id, String firstName, String lastName, String jmbg,
//                           String password, String email, String address, String city,
//                           String country, String telephone, ClinicDTO clinic) {
//        super(id, firstName, lastName, jmbg, password, email, address, city, country, telephone);
//        this.clinic = clinic;
//    }
}
