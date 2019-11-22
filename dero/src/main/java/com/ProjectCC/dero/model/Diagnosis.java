package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.DiagnosisDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "diagnosis")
public class Diagnosis {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "description", nullable = false)
   private String description;

   @Column(name = "code", nullable = false, unique = true)
   private String code;

   @ManyToMany(mappedBy = "diagnosis")
   public Set<Examination> examinations;

   public Diagnosis() {
   }

   public Diagnosis(DiagnosisDTO diagnosisDTO) {
      this.id = diagnosisDTO.getId();
      this.name = diagnosisDTO.getName();
      this.description = diagnosisDTO.getDescription();
      this.code = diagnosisDTO.getCode();
   }
}