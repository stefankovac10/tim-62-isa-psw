package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Getter
@Setter
public class MedicalStaff extends SecurityProperties.User {
   public Clinic clinic;

   public MedicalStaff() {
   }
}