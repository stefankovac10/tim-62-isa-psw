package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class ClinicCenter {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToMany(mappedBy = "clinicCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<ClinicCenterAdministrator> admins;

   @OneToMany(mappedBy = "clinicCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Clinic> clinics;

   @OneToMany(mappedBy = "clinicCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<MedicalStaff> medicalStaff;

   @OneToMany(mappedBy = "clinicCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<MedicalRecord> medicalRecords;

   @OneToMany(mappedBy = "clinicCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Medication> medicationCodebook;

   @OneToMany(mappedBy = "clinicCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Diagnosis> diagnosisCodebook;

   public ClinicCenter() {
   }
}