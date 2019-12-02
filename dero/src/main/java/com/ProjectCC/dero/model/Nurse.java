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

    @Autowired
    public Nurse() {
    }

    public Nurse(NurseDTO nurseDTO) {
        super(nurseDTO.getFirstName(), nurseDTO.getLastName(), nurseDTO.getJmbg(), nurseDTO.getPassword(), nurseDTO.getEmail(),
                nurseDTO.getAddress(), nurseDTO.getCity(), nurseDTO.getCountry(), nurseDTO.getTelephone());
    }
}