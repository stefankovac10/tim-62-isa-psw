package com.ProjectCC.dero.model;

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

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "clinicCenter_id", nullable = false)
   public ClinicCenter clinicCenter;

   /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "examination_id", nullable = false)*/
   @ManyToMany(mappedBy = "diagnosis")
   public Set<Examination> examinations;

   public Diagnosis() {
   }

   public Diagnosis(String name, String description, String code) {
      this.name = name;
      this.description = description;
      this.code = code;
   }
}