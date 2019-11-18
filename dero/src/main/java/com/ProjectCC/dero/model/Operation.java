package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Operation {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date", nullable = false)
   private String date;

   @Column(name = "duration", nullable = false)
   private String duration;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "medRec_id", nullable = false)
   public MedicalRecord medicalRecord;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "OR_id", nullable = false)
   public OperationRoom operationRoom;

   @ManyToMany
   @JoinTable(
           name = "operations_doctors",
           joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id")
   )
   public Set<Doctor> doctors;

   @OneToOne()
   @JoinColumn(name = "patient_id", referencedColumnName = "id")
   public Patient patient;

   public Operation() {
   }

}