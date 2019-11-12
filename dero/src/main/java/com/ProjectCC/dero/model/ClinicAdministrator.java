package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import java.util.Set;

@Getter
@Setter
public class ClinicAdministrator extends SecurityProperties.User {
   public Clinic clinic;
   public Set<VacationRequest> vacationRequests;

   public ClinicAdministrator() {
   }
}