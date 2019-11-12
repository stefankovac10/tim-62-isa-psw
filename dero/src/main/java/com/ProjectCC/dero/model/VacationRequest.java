package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacationRequest {
   private String date;
   private String duration;
   public MedicalStaff medicalStaff;

   public VacationRequest() {
   }
}