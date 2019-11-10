package com.ProjectCC.dero.model; /***********************************************************************
 * Module:  MedicalRecord.java
 * Author:  Bax
 * Purpose: Defines the Class MedicalRecord
 ***********************************************************************/

public class MedicalRecord {
   private int height;
   private int weight;
   private String bloodType;
   private String allergies;
   private String diopter;
   
   public java.util.List<Nurse> nurses;
   public java.util.List<Doctor> doctors;
   public java.util.List<Examination> examinations;
   public java.util.List<Operation> operations;

}