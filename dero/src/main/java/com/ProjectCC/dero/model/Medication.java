package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.MedicationDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="medication")
public class Medication {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name="name", nullable = false)
   private String name;

   @Column(name = "description", nullable = false)
   private String description;

   @Column(name = "code", nullable = false, unique = true)
   private String code;

   @ManyToMany(mappedBy = "medication")
   public Set<Prescription> prescriptions;

   public Medication() {
   }

   public Medication(MedicationDTO medicationDTO) {
      this.name = medicationDTO.getName();
      this.description = medicationDTO.getDescription();
      this.id = medicationDTO.getId();
      this.code = medicationDTO.getCode();
   }
}