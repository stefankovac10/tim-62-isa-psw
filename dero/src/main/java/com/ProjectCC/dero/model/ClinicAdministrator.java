package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.ClinicAdministratorDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class ClinicAdministrator extends User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "clinic_id", nullable = false)
   public Clinic clinic;

   @OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<VacationRequest> vacationRequests;

   public ClinicAdministrator() {
   }

   public ClinicAdministrator(ClinicAdministratorDTO cadminDTO){
      super(cadminDTO.getFirstName(), cadminDTO.getLastName(),cadminDTO.getJmbg(),cadminDTO.getPassword(),cadminDTO.getEmail(),cadminDTO.getAddress(),cadminDTO.getCity(),cadminDTO.getCountry(),cadminDTO.getTelephone());
   }
}