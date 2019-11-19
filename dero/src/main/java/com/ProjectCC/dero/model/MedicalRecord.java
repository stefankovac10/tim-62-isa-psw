package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class MedicalRecord {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "height", nullable = false)
   private int height;

   @Column(name = "weight", nullable = false)
   private int weight;

   @Column(name = "bloodType", nullable = false)
   private String bloodType;

   // allergies should be an array
   /*@Column(name = "allergies", nullable = false)
   private String allergies;*/

   @Column(name = "diopter", nullable = false)
   private String diopter;

   @OneToOne(mappedBy = "medicalRecord")
   public Patient patient;

   // instead of nurses and doctors, there is set of medicalStaffs
   // public Set<Nurse> nurses;
   // public Set<Doctor> doctors;

   @ManyToMany()
   @JoinTable(
           name = "medicalRecords_medicalStaffs",
           joinColumns = @JoinColumn(name = "medStaff_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "medRec_id", referencedColumnName = "id")
   )
   public Set<MedicalStaff> medicalStaffs;

   @OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Examination> examinations;

   @OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Operation> operations;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "clinicCenter_id", nullable = false)
   public ClinicCenter clinicCenter;

   public MedicalRecord() {
   }
}