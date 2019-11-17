package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MedicalStaff extends User {


   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Clinic clinic;

   @OneToOne(mappedBy = "medicalStaff")
   private VacationRequest vacationRequest;

   public MedicalStaff() {
   }
}