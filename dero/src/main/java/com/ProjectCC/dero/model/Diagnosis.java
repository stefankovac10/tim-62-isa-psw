package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.DiagnosisDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "diagnosis")
@AllArgsConstructor
@NoArgsConstructor
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

   @OneToOne(mappedBy = "diagnosis")
   public Examination examination;

   /*
   public Diagnosis() {
   }

   public Diagnosis(DiagnosisDTO diagnosisDTO) {
      this.id = diagnosisDTO.getId();
      this.name = diagnosisDTO.getName();
      this.description = diagnosisDTO.getDescription();
      this.code = diagnosisDTO.getCode();
   }*/
}