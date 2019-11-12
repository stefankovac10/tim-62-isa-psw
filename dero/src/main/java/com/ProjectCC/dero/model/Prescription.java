package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class Prescription {
   public Prescription() {
   }

   public Set<Medication> medication;
   public Doctor doctor;
   public Nurse nurse;

}