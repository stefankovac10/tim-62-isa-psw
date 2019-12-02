package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.NurseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Nurse extends MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "nurse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Examination> examinations;

    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "medRec_id", nullable = false)
    private MedicalRecord medicalRecord;*/

    public Nurse() {
    }

    @Autowired
    public Nurse(String firstName, String lastName, String jmbg, String password, String email,
                  String address, String city, String country, String telephone) {
        super(firstName, lastName, jmbg, password, email, address,city, country, telephone);
    }

    public Nurse(NurseDTO nurseDTO){
        this(nurseDTO.getFirstName(), nurseDTO.getLastName(), nurseDTO.getJmbg(), nurseDTO.getPassword(), nurseDTO.getEmail(),
                nurseDTO.getAddress(), nurseDTO.getCity(), nurseDTO.getCountry(), nurseDTO.getTelephone());
    }
}