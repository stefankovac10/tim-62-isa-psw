package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.ClinicAdministratorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ClinicAdministrator extends User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "clinic_id", nullable = false)
   public Clinic clinic;

}