package com.ProjectCC.dero.model; /***********************************************************************
 * Module:  MedicalStaff.java
 * Author:  Bax
 * Purpose: Defines the Class MedicalStaff
 ***********************************************************************/

import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class MedicalStaff extends SecurityProperties.User {
   public Clinic clinic;

}