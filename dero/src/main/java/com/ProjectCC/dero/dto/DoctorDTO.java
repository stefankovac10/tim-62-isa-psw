package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;


public class DoctorDTO extends MedicalStaffDTO {
    private Long id;
    private int marks;

    @Autowired
    public DoctorDTO() {
        super();
    }

    public DoctorDTO(String firstName, String lastName, String jmbg, String password,
                     String email, String address, String city, String country,
                     String telephone) {
        super(firstName, lastName, jmbg, password, email, address, city, country,
                telephone);
    }

    @Autowired
    public DoctorDTO(Long id, String firstName, String lastName, String jmbg, String password,
                     String email, String address, String city, String country,
                     String telephone, Clinic clinic) {
        super(id, firstName, lastName, jmbg, password, email, address, city, country,
                telephone, clinic);
    }

    @Autowired
    public DoctorDTO(Doctor doctor) {
        super(doctor.getId(), doctor.getFirstName(), doctor.getLastName(), doctor.getJmbg(),
                doctor.getPassword(), doctor.getEmail(), doctor.getAddress(),
                doctor.getCity(), doctor.getCountry(), doctor.getTelephone(), doctor.getClinic());
    }
}
