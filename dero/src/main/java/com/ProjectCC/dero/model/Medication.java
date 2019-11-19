package com.ProjectCC.dero.model;

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

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "clinicCenter_id", nullable = false)
   public ClinicCenter clinicCenter;

   @ManyToMany(mappedBy = "medication")
   public Set<Prescription> prescriptions;

   public Medication() {
   }

   public Medication(String name, String description, String code) {
      this.name = name;
      this.description = description;
      this.code = code;
   }
}