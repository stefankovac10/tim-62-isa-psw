/***********************************************************************
 * Module:  Prescription.java
 * Author:  Bax
 * Purpose: Defines the Class Prescription
 ***********************************************************************/

import java.util.*;

/** @pdOid 450ad41c-5a87-43cf-9cb4-26694f9af361 */
public class Prescription {
   /** @pdOid dfe1c55a-c55b-498b-8dd3-07b2be653c84
       @pdRoleInfo migr=yes name=Medication assc=association7 coll=java.util.List impl=java.util.ArrayList mult=0..* type=Composition */
   public java.util.List<Medication> medication;
   /** @pdOid 4235d8c0-c797-4644-9c96-d95a4113adaf
       @pdRoleInfo migr=yes name=Doctor assc=association9 mult=1..1 */
   public Doctor doctor;
   /** @pdOid b6602162-b5fc-49ad-9e67-cfd73b74b1d0
       @pdRoleInfo migr=yes name=Nurse assc=association8 mult=1..1 */
   public Nurse nurse;
   
   
   /** @pdGenerated default getter */
   public java.util.List<Medication> getMedication() {
      if (medication == null)
         medication = new java.util.ArrayList<Medication>();
      return medication;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMedication() {
      if (medication == null)
         medication = new java.util.ArrayList<Medication>();
      return medication.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMedication */
   public void setMedication(java.util.List<Medication> newMedication) {
      removeAllMedication();
      for (java.util.Iterator iter = newMedication.iterator(); iter.hasNext();)
         addMedication((Medication)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMedication */
   public void addMedication(Medication newMedication) {
      if (newMedication == null)
         return;
      if (this.medication == null)
         this.medication = new java.util.ArrayList<Medication>();
      if (!this.medication.contains(newMedication))
         this.medication.add(newMedication);
   }
   
   /** @pdGenerated default remove
     * @param oldMedication */
   public void removeMedication(Medication oldMedication) {
      if (oldMedication == null)
         return;
      if (this.medication != null)
         if (this.medication.contains(oldMedication))
            this.medication.remove(oldMedication);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMedication() {
      if (medication != null)
         medication.clear();
   }

}