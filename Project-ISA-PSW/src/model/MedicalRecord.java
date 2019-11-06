/***********************************************************************
 * Module:  MedicalRecord.java
 * Author:  Bax
 * Purpose: Defines the Class MedicalRecord
 ***********************************************************************/

import java.util.*;

/** @pdOid d0addb5e-009d-4c9b-b0d3-20734bb8b041 */
public class MedicalRecord {
   /** @pdOid 00b15251-0c1d-447d-b707-56405de2aea4 */
   private int height;
   /** @pdOid e0c3a90e-f84d-4854-8fcf-6c5e58a42027 */
   private int weight;
   /** @pdOid 8b021f32-5a6d-4926-9e85-35b5bfb5d370 */
   private String bloodType;
   /** @pdOid 3ab2d58f-0f31-47f1-bb10-f98831a7a7a7 */
   private String allergies;
   /** @pdOid 5c60d779-2ccf-4941-9d5a-60af2c42bbeb */
   private String diopter;
   
   /** @pdOid c7bfcde0-7252-44a3-a5df-b4436597df11
       @pdRoleInfo migr=yes name=Nurse assc=association13 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public java.util.List<Nurse> nurses;
   /** @pdOid 072f2103-2c0c-4c44-967b-cd1942191fb0
       @pdRoleInfo migr=yes name=Doctor assc=association14 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public java.util.List<Doctor> doctors;
   /** @pdOid 413f4314-1c20-47de-bf3b-5050335f857f
       @pdRoleInfo migr=yes name=Examination assc=association16 coll=java.util.List impl=java.util.ArrayList mult=0..* type=Composition */
   public java.util.List<Examination> examinations;
   /** @pdOid 10e6e50c-6ec9-44a7-ad10-5cc8ab54c0e0
       @pdRoleInfo migr=yes name=Operation assc=association24 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public java.util.List<Operation> operations;
   
   
   /** @pdGenerated default getter */
   public java.util.List<Nurse> getNurses() {
      if (nurses == null)
         nurses = new java.util.ArrayList<Nurse>();
      return nurses;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorNurses() {
      if (nurses == null)
         nurses = new java.util.ArrayList<Nurse>();
      return nurses.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newNurses */
   public void setNurses(java.util.List<Nurse> newNurses) {
      removeAllNurses();
      for (java.util.Iterator iter = newNurses.iterator(); iter.hasNext();)
         addNurses((Nurse)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newNurse */
   public void addNurses(Nurse newNurse) {
      if (newNurse == null)
         return;
      if (this.nurses == null)
         this.nurses = new java.util.ArrayList<Nurse>();
      if (!this.nurses.contains(newNurse))
         this.nurses.add(newNurse);
   }
   
   /** @pdGenerated default remove
     * @param oldNurse */
   public void removeNurses(Nurse oldNurse) {
      if (oldNurse == null)
         return;
      if (this.nurses != null)
         if (this.nurses.contains(oldNurse))
            this.nurses.remove(oldNurse);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllNurses() {
      if (nurses != null)
         nurses.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Doctor> getDoctors() {
      if (doctors == null)
         doctors = new java.util.ArrayList<Doctor>();
      return doctors;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDoctors() {
      if (doctors == null)
         doctors = new java.util.ArrayList<Doctor>();
      return doctors.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDoctors */
   public void setDoctors(java.util.List<Doctor> newDoctors) {
      removeAllDoctors();
      for (java.util.Iterator iter = newDoctors.iterator(); iter.hasNext();)
         addDoctors((Doctor)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDoctor */
   public void addDoctors(Doctor newDoctor) {
      if (newDoctor == null)
         return;
      if (this.doctors == null)
         this.doctors = new java.util.ArrayList<Doctor>();
      if (!this.doctors.contains(newDoctor))
         this.doctors.add(newDoctor);
   }
   
   /** @pdGenerated default remove
     * @param oldDoctor */
   public void removeDoctors(Doctor oldDoctor) {
      if (oldDoctor == null)
         return;
      if (this.doctors != null)
         if (this.doctors.contains(oldDoctor))
            this.doctors.remove(oldDoctor);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDoctors() {
      if (doctors != null)
         doctors.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Examination> getExaminations() {
      if (examinations == null)
         examinations = new java.util.ArrayList<Examination>();
      return examinations;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorExaminations() {
      if (examinations == null)
         examinations = new java.util.ArrayList<Examination>();
      return examinations.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newExaminations */
   public void setExaminations(java.util.List<Examination> newExaminations) {
      removeAllExaminations();
      for (java.util.Iterator iter = newExaminations.iterator(); iter.hasNext();)
         addExaminations((Examination)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newExamination */
   public void addExaminations(Examination newExamination) {
      if (newExamination == null)
         return;
      if (this.examinations == null)
         this.examinations = new java.util.ArrayList<Examination>();
      if (!this.examinations.contains(newExamination))
         this.examinations.add(newExamination);
   }
   
   /** @pdGenerated default remove
     * @param oldExamination */
   public void removeExaminations(Examination oldExamination) {
      if (oldExamination == null)
         return;
      if (this.examinations != null)
         if (this.examinations.contains(oldExamination))
            this.examinations.remove(oldExamination);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllExaminations() {
      if (examinations != null)
         examinations.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Operation> getOperations() {
      if (operations == null)
         operations = new java.util.ArrayList<Operation>();
      return operations;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorOperations() {
      if (operations == null)
         operations = new java.util.ArrayList<Operation>();
      return operations.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newOperations */
   public void setOperations(java.util.List<Operation> newOperations) {
      removeAllOperations();
      for (java.util.Iterator iter = newOperations.iterator(); iter.hasNext();)
         addOperations((Operation)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newOperation */
   public void addOperations(Operation newOperation) {
      if (newOperation == null)
         return;
      if (this.operations == null)
         this.operations = new java.util.ArrayList<Operation>();
      if (!this.operations.contains(newOperation))
         this.operations.add(newOperation);
   }
   
   /** @pdGenerated default remove
     * @param oldOperation */
   public void removeOperations(Operation oldOperation) {
      if (oldOperation == null)
         return;
      if (this.operations != null)
         if (this.operations.contains(oldOperation))
            this.operations.remove(oldOperation);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOperations() {
      if (operations != null)
         operations.clear();
   }

}