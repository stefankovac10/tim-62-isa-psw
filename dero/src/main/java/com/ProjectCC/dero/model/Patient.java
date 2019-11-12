package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Patient extends User {
   public MedicalRecord medicalRecord;

   public Patient() {
   }
}