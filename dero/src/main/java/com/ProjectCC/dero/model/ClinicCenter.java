package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class ClinicCenter {
   public Set<ClinicCenterAdministrator> admins;
   public Set<Clinic> clinics;
   public Set<MedicalStaff> medicalStaff;
   public Set<MedicalRecord> medicalRecords;
   public Set<Medication> medicationCodebook;
   public Set<Diagnosis> diagnosisCodebook;

   public ClinicCenter() {
   }
}