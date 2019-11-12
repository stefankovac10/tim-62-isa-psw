package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class MedicalRecord {
   private int height;
   private int weight;
   private String bloodType;
   private String allergies;
   private String diopter;
   
   public Set<Nurse> nurses;
   public Set<Doctor> doctors;
   public Set<Examination> examinations;
   public Set<Operation> operations;

   public MedicalRecord() {
   }
}