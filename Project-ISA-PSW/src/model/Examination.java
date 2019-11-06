/***********************************************************************
 * Module:  Examination.java
 * Author:  Bax
 * Purpose: Defines the Class Examination
 ***********************************************************************/

import java.util.*;

/** @pdOid 4e369b21-2d92-4450-a743-32fc535d938d */
public class Examination {
   /** @pdOid fef08238-5062-4389-9acb-df01a0cc662f */
   private String date;
   /** @pdOid b447b5db-0fa7-4380-841d-912efdff1720 */
   private String type;
   /** @pdOid a2286eb9-727e-4884-86d4-c2bbd8c61fd0 */
   private String duration;
   /** @pdOid ef19b483-f5cb-43ed-bb97-0329d793428c */
   private String price;
   /** @pdOid 45513ebe-d452-450d-b56f-d791694696f8 */
   private String discount;
   /** @pdOid de7c9795-739e-4d70-9d3e-c86384d5fd76 */
   private String report;
   
   /** @pdOid c21de2bd-29d0-4723-a3ff-8843172d9f97
       @pdRoleInfo migr=yes name=ExaminationRoom assc=association5 mult=0..1 */
   public ExaminationRoom examinationRoom;
   /** @pdOid 94665d85-b2f8-49e2-9a28-ef1919770a25
       @pdRoleInfo migr=yes name=Diagnosis assc=association6 coll=java.util.List impl=java.util.ArrayList mult=0..* type=Composition */
   public java.util.List<Diagnosis> diagnosis;
   /** @pdOid 36977703-9639-44b0-a49e-bac3a806ff0b
       @pdRoleInfo migr=yes name=Prescription assc=association10 mult=0..1 */
   public Prescription prescription;
   /** @pdOid 3edf77a5-1896-4908-9470-53e037b9eef6
       @pdRoleInfo migr=yes name=Nurse assc=association11 mult=1..1 */
   public Nurse nurse;
   /** @pdOid cedbb28a-9b41-4d6d-b114-7535d07c0acd
       @pdRoleInfo migr=yes name=Doctor assc=association12 mult=1..1 */
   public Doctor doctor;
   /** @pdOid 000b18ee-7a91-4f73-ad2c-5b7110cdf0e5
       @pdRoleInfo migr=yes name=Patient assc=association17 mult=1..1 */
   public Patient patient;
   
   
   /** @pdGenerated default getter */
   public java.util.List<Diagnosis> getDiagnosis() {
      if (diagnosis == null)
         diagnosis = new java.util.ArrayList<Diagnosis>();
      return diagnosis;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDiagnosis() {
      if (diagnosis == null)
         diagnosis = new java.util.ArrayList<Diagnosis>();
      return diagnosis.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDiagnosis */
   public void setDiagnosis(java.util.List<Diagnosis> newDiagnosis) {
      removeAllDiagnosis();
      for (java.util.Iterator iter = newDiagnosis.iterator(); iter.hasNext();)
         addDiagnosis((Diagnosis)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDiagnosis */
   public void addDiagnosis(Diagnosis newDiagnosis) {
      if (newDiagnosis == null)
         return;
      if (this.diagnosis == null)
         this.diagnosis = new java.util.ArrayList<Diagnosis>();
      if (!this.diagnosis.contains(newDiagnosis))
         this.diagnosis.add(newDiagnosis);
   }
   
   /** @pdGenerated default remove
     * @param oldDiagnosis */
   public void removeDiagnosis(Diagnosis oldDiagnosis) {
      if (oldDiagnosis == null)
         return;
      if (this.diagnosis != null)
         if (this.diagnosis.contains(oldDiagnosis))
            this.diagnosis.remove(oldDiagnosis);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDiagnosis() {
      if (diagnosis != null)
         diagnosis.clear();
   }

}