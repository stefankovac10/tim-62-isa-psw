/***********************************************************************
 * Module:  ClinicCenter.java
 * Author:  Bax
 * Purpose: Defines the Class ClinicCenter
 ***********************************************************************/

import java.util.*;

/** @pdOid 70189916-549a-4f88-8da0-f9fb27215da7 */
public class ClinicCenter {
   /** @pdOid 1bf6c596-fb10-41e8-b0b8-500f3332b250
       @pdRoleInfo migr=yes name=ClinicCenterAdministrator assc=association3 coll=java.util.List impl=java.util.ArrayList mult=1..* type=Composition */
   public java.util.List<ClinicCenterAdministrator> admins;
   /** @pdOid aae0ae19-061f-4533-8c17-82b562ba8508
       @pdRoleInfo migr=yes name=Clinic assc=association2 coll=java.util.List impl=java.util.ArrayList mult=1..* type=Composition */
   public java.util.List<Clinic> clinics;
   /** @pdOid 90b3274f-fee2-4fdc-973a-73ae929dadfa
       @pdRoleInfo migr=yes name=MedicalStaff assc=association22 coll=java.util.List impl=java.util.ArrayList mult=0..* type=Composition */
   public java.util.List<MedicalStaff> medicalStaff;
   /** @pdOid ec3fb0b1-bea2-4b0a-a146-0db0724de3ee
       @pdRoleInfo migr=yes name=MedicalRecord assc=association25 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public java.util.List<MedicalRecord> medicalRecords;
   /** @pdOid b5946b92-92b7-4861-834b-1a165bb60f7e
       @pdRoleInfo migr=yes name=Medication assc=association26 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Medication> medicationCodebook;
   /** @pdOid e50fc582-480d-479f-92f4-19b527cfb9c4
       @pdRoleInfo migr=yes name=Diagnosis assc=association27 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Diagnosis> diagnosisCodebook;
   
   
   /** @pdGenerated default getter */
   public java.util.List<Clinic> getClinics() {
      if (clinics == null)
         clinics = new java.util.ArrayList<Clinic>();
      return clinics;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorClinics() {
      if (clinics == null)
         clinics = new java.util.ArrayList<Clinic>();
      return clinics.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newClinics */
   public void setClinics(java.util.List<Clinic> newClinics) {
      removeAllClinics();
      for (java.util.Iterator iter = newClinics.iterator(); iter.hasNext();)
         addClinics((Clinic)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newClinic */
   public void addClinics(Clinic newClinic) {
      if (newClinic == null)
         return;
      if (this.clinics == null)
         this.clinics = new java.util.ArrayList<Clinic>();
      if (!this.clinics.contains(newClinic))
         this.clinics.add(newClinic);
   }
   
   /** @pdGenerated default remove
     * @param oldClinic */
   public void removeClinics(Clinic oldClinic) {
      if (oldClinic == null)
         return;
      if (this.clinics != null)
         if (this.clinics.contains(oldClinic))
            this.clinics.remove(oldClinic);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllClinics() {
      if (clinics != null)
         clinics.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<ClinicCenterAdministrator> getAdmins() {
      if (admins == null)
         admins = new java.util.ArrayList<ClinicCenterAdministrator>();
      return admins;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAdmins() {
      if (admins == null)
         admins = new java.util.ArrayList<ClinicCenterAdministrator>();
      return admins.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAdmins */
   public void setAdmins(java.util.List<ClinicCenterAdministrator> newAdmins) {
      removeAllAdmins();
      for (java.util.Iterator iter = newAdmins.iterator(); iter.hasNext();)
         addAdmins((ClinicCenterAdministrator)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newClinicCenterAdministrator */
   public void addAdmins(ClinicCenterAdministrator newClinicCenterAdministrator) {
      if (newClinicCenterAdministrator == null)
         return;
      if (this.admins == null)
         this.admins = new java.util.ArrayList<ClinicCenterAdministrator>();
      if (!this.admins.contains(newClinicCenterAdministrator))
         this.admins.add(newClinicCenterAdministrator);
   }
   
   /** @pdGenerated default remove
     * @param oldClinicCenterAdministrator */
   public void removeAdmins(ClinicCenterAdministrator oldClinicCenterAdministrator) {
      if (oldClinicCenterAdministrator == null)
         return;
      if (this.admins != null)
         if (this.admins.contains(oldClinicCenterAdministrator))
            this.admins.remove(oldClinicCenterAdministrator);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAdmins() {
      if (admins != null)
         admins.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<MedicalStaff> getMedicalStaff() {
      if (medicalStaff == null)
         medicalStaff = new java.util.ArrayList<MedicalStaff>();
      return medicalStaff;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedicalStaff() {
      if (medicalStaff == null)
         medicalStaff = new java.util.ArrayList<MedicalStaff>();
      return medicalStaff.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMedicalStaff */
   public void setMedicalStaff(java.util.List<MedicalStaff> newMedicalStaff) {
      removeAllMedicalStaff();
      for (java.util.Iterator iter = newMedicalStaff.iterator(); iter.hasNext();)
         addMedicalStaff((MedicalStaff)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedicalStaff */
   public void addMedicalStaff(MedicalStaff newMedicalStaff) {
      if (newMedicalStaff == null)
         return;
      if (this.medicalStaff == null)
         this.medicalStaff = new java.util.ArrayList<MedicalStaff>();
      if (!this.medicalStaff.contains(newMedicalStaff))
         this.medicalStaff.add(newMedicalStaff);
   }
   
   /** @pdGenerated default remove
     * @param oldMedicalStaff */
   public void removeMedicalStaff(MedicalStaff oldMedicalStaff) {
      if (oldMedicalStaff == null)
         return;
      if (this.medicalStaff != null)
         if (this.medicalStaff.contains(oldMedicalStaff))
            this.medicalStaff.remove(oldMedicalStaff);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMedicalStaff() {
      if (medicalStaff != null)
         medicalStaff.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<MedicalRecord> getMedicalRecords() {
      if (medicalRecords == null)
         medicalRecords = new java.util.ArrayList<MedicalRecord>();
      return medicalRecords;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedicalRecords() {
      if (medicalRecords == null)
         medicalRecords = new java.util.ArrayList<MedicalRecord>();
      return medicalRecords.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMedicalRecords */
   public void setMedicalRecords(java.util.List<MedicalRecord> newMedicalRecords) {
      removeAllMedicalRecords();
      for (java.util.Iterator iter = newMedicalRecords.iterator(); iter.hasNext();)
         addMedicalRecords((MedicalRecord)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedicalRecord */
   public void addMedicalRecords(MedicalRecord newMedicalRecord) {
      if (newMedicalRecord == null)
         return;
      if (this.medicalRecords == null)
         this.medicalRecords = new java.util.ArrayList<MedicalRecord>();
      if (!this.medicalRecords.contains(newMedicalRecord))
         this.medicalRecords.add(newMedicalRecord);
   }
   
   /** @pdGenerated default remove
     * @param oldMedicalRecord */
   public void removeMedicalRecords(MedicalRecord oldMedicalRecord) {
      if (oldMedicalRecord == null)
         return;
      if (this.medicalRecords != null)
         if (this.medicalRecords.contains(oldMedicalRecord))
            this.medicalRecords.remove(oldMedicalRecord);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMedicalRecords() {
      if (medicalRecords != null)
         medicalRecords.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Medication> getMedicationCodebook() {
      if (medicationCodebook == null)
         medicationCodebook = new java.util.HashSet<Medication>();
      return medicationCodebook;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedicationCodebook() {
      if (medicationCodebook == null)
         medicationCodebook = new java.util.HashSet<Medication>();
      return medicationCodebook.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMedicationCodebook */
   public void setMedicationCodebook(java.util.Collection<Medication> newMedicationCodebook) {
      removeAllMedicationCodebook();
      for (java.util.Iterator iter = newMedicationCodebook.iterator(); iter.hasNext();)
         addMedicationCodebook((Medication)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedication */
   public void addMedicationCodebook(Medication newMedication) {
      if (newMedication == null)
         return;
      if (this.medicationCodebook == null)
         this.medicationCodebook = new java.util.HashSet<Medication>();
      if (!this.medicationCodebook.contains(newMedication))
         this.medicationCodebook.add(newMedication);
   }
   
   /** @pdGenerated default remove
     * @param oldMedication */
   public void removeMedicationCodebook(Medication oldMedication) {
      if (oldMedication == null)
         return;
      if (this.medicationCodebook != null)
         if (this.medicationCodebook.contains(oldMedication))
            this.medicationCodebook.remove(oldMedication);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMedicationCodebook() {
      if (medicationCodebook != null)
         medicationCodebook.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Diagnosis> getDiagnosisCodebook() {
      if (diagnosisCodebook == null)
         diagnosisCodebook = new java.util.HashSet<Diagnosis>();
      return diagnosisCodebook;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDiagnosisCodebook() {
      if (diagnosisCodebook == null)
         diagnosisCodebook = new java.util.HashSet<Diagnosis>();
      return diagnosisCodebook.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDiagnosisCodebook */
   public void setDiagnosisCodebook(java.util.Collection<Diagnosis> newDiagnosisCodebook) {
      removeAllDiagnosisCodebook();
      for (java.util.Iterator iter = newDiagnosisCodebook.iterator(); iter.hasNext();)
         addDiagnosisCodebook((Diagnosis)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDiagnosis */
   public void addDiagnosisCodebook(Diagnosis newDiagnosis) {
      if (newDiagnosis == null)
         return;
      if (this.diagnosisCodebook == null)
         this.diagnosisCodebook = new java.util.HashSet<Diagnosis>();
      if (!this.diagnosisCodebook.contains(newDiagnosis))
         this.diagnosisCodebook.add(newDiagnosis);
   }
   
   /** @pdGenerated default remove
     * @param oldDiagnosis */
   public void removeDiagnosisCodebook(Diagnosis oldDiagnosis) {
      if (oldDiagnosis == null)
         return;
      if (this.diagnosisCodebook != null)
         if (this.diagnosisCodebook.contains(oldDiagnosis))
            this.diagnosisCodebook.remove(oldDiagnosis);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDiagnosisCodebook() {
      if (diagnosisCodebook != null)
         diagnosisCodebook.clear();
   }

}