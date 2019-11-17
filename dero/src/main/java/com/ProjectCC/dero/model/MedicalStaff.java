package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class MedicalStaff extends User {


   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Clinic clinic;

   @OneToOne(mappedBy = "medicalStaff")
   private VacationRequest vacationRequest;

   @Autowired
   public MedicalStaff() {
   }

   @Autowired
   public MedicalStaff(Clinic clinic, VacationRequest vacationRequest) {
      this.clinic = clinic;
      this.vacationRequest = vacationRequest;
   }

   @Autowired
   public MedicalStaff(String firstName, String lastName, String jmbg, String password, String email,
                       String address, String city, String country, String telephone) {
      super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
   }
}