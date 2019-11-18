package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Doctor extends MedicalStaff {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private int marks;

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
}