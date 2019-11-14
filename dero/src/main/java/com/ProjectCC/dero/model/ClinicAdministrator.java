package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class ClinicAdministrator extends SecurityProperties.User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   // @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Clinic clinic;

   @OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<VacationRequest> vacationRequests;

   public ClinicAdministrator() {
   }
}