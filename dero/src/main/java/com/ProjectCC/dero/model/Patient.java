package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Patient extends User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne()
   @JoinColumn(name = "medRec_id", referencedColumnName = "id")
   public MedicalRecord medicalRecord;

   @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Examination> examinations;

   @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Operation> operations;


   public Patient() {
   }

   public Patient(String firstName, String lastName, String jmbg, String password, String email, String address, String city, String country, String telephone) {
      super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
   }


}