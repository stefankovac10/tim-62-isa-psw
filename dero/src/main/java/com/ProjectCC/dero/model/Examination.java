package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class Examination {
   private String date;
   private String type;
   private String duration;
   private String price;
   private String discount;
   private String report;
   public ExaminationRoom examinationRoom;
   public Set<Diagnosis> diagnosis;
   public Prescription prescription;
   public Nurse nurse;
   public Doctor doctor;
   public Patient patient;

   public Examination() {
   }
}