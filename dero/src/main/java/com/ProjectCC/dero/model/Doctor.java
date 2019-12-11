package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.DoctorDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Doctor extends MedicalStaff {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "grade")
   private Double grade;

   @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Examination> examinations;

   /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "medRec_id", nullable = false)
   private MedicalRecord medicalRecord;*/

   @ManyToMany(mappedBy = "doctors")
   public Set<Operation> operations;

   @ManyToOne
   @JoinColumn(name = "specialisedType_id", nullable = true)
   private TypeOfExamination specialisedType;

   @Autowired
   public Doctor() {
   }

   @Autowired
   public Doctor(String firstName, String lastName, String jmbg, String password, String email,
                 String address, String city, String country, String telephone) {
      super(firstName, lastName, jmbg, password, email, address,city, country, telephone);
   }

   @Autowired
   public Doctor(DoctorDTO doctorDTO){
      this(doctorDTO.getFirstName(),doctorDTO.getLastName(),doctorDTO.getJmbg(), doctorDTO.getPassword(),doctorDTO.getEmail(),
              doctorDTO.getAddress(), doctorDTO.getCity(), doctorDTO.getCountry(), doctorDTO.getTelephone());
   }

}