package com.ProjectCC.dero.model; /***********************************************************************
 * Module:  ClinicAdministrator.java
 * Author:  Bax
 * Purpose: Defines the Class ClinicAdministrator
 ***********************************************************************/

import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class ClinicAdministrator extends SecurityProperties.User {
   public Clinic clinic;
   public java.util.List<VacationRequest> vacationRequests;

}