package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Prescription {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "certified")
   private Boolean certified;

   @OneToOne(mappedBy = "prescription")
   public Examination examination;

   @ManyToMany
   @JoinTable(
           name = "prescriptions_medication",
           joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "id")
   )
   public Set<Medication> medication;

   @OneToOne()
   @JoinColumn(name = "doctor_id", referencedColumnName = "id")
   public Doctor doctor;

   @OneToOne()
   @JoinColumn(name = "nurse_id", referencedColumnName = "id")
   public Nurse nurse;

   public Prescription() {
   }
}