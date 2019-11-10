package com.ProjectCC.dero.model; /***********************************************************************
 * Module:  Examination.java
 * Author:  Bax
 * Purpose: Defines the Class Examination
 ***********************************************************************/

public class Examination {
   private String date;
   private String type;
   private String duration;
   private String price;
   private String discount;
   private String report;
   public ExaminationRoom examinationRoom;
   public java.util.List<Diagnosis> diagnosis;
   public Prescription prescription;
   public Nurse nurse;
   public Doctor doctor;
   public Patient patient;
}