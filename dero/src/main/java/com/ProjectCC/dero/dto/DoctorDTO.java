package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO extends MedicalStaffDTO {
    private Long id;
    private Double marks;
    public List<ExaminationDTO> examinations;
    public Set<OperationDTO> operations;
    private TypeOfExaminationDTO specialisedType;

//    @Autowired
//    public DoctorDTO() {
//        super();
//    }
//
//    public DoctorDTO(String firstName, String lastName, String jmbg, String password,
//                       String email, String address, String city, String country,
//                       String telephone) {
//        super(firstName, lastName, jmbg, password, email, address, city, country,
//                telephone);
//    }
//
//    @Autowired
//    public DoctorDTO(Long id, String firstName, String lastName, String jmbg, String password,
//                     String email, String address, String city, String country,
//                     String telephone, ClinicDTO clinic) {
//        super(id, firstName, lastName, jmbg, password, email, address, city, country,
//                telephone, clinic);
//    }
//
//    @Autowired
//    public DoctorDTO(Doctor doctor) {
//        super(doctor.getId(), doctor.getFirstName(), doctor.getLastName(), doctor.getJmbg(),
//                doctor.getPassword(), doctor.getEmail(), doctor.getAddress(),
//                doctor.getCity(), doctor.getCountry(), doctor.getTelephone(), new ClinicDTO(doctor.getClinic()));
//    }
}
