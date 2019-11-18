package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Examination {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date", nullable = false)
   private String date;

   @Column(name = "type", nullable = false)
   private String type;

   @Column(name = "duration", nullable = false)
   private String duration;

   @Column(name = "price", nullable = false)
   private String price;

   @Column(name = "discount", nullable = false)
   private String discount;

   @Column(name = "report", nullable = false)
   private String report;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "ER_id", nullable = false)
   public ExaminationRoom examinationRoom;

   //@OneToMany(mappedBy = "examination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @ManyToMany
   @JoinTable(
           name = "examinations_diagnosis",
           joinColumns = @JoinColumn(name = "diagnosis_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "examination_id", referencedColumnName = "id")
   )
   public Set<Diagnosis> diagnosis;

   @OneToOne()
   @JoinColumn(name = "prescription_id", referencedColumnName = "id")
   public Prescription prescription;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "nurse_id", referencedColumnName = "id")
   public Nurse nurse;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "doctor_id", referencedColumnName = "id")
   public Doctor doctor;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "patient_id", referencedColumnName = "id")
   public Patient patient;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "medRec_id", nullable = false)
   public MedicalRecord medicalRecord;

   public Examination() {
   }
}