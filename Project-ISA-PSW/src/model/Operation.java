/***********************************************************************
 * Module:  Operation.java
 * Author:  Bax
 * Purpose: Defines the Class Operation
 ***********************************************************************/

import java.util.*;

/** @pdOid 856f3883-3f22-4327-8498-091bdbf8ec29 */
public class Operation {
   /** @pdOid aa7c9ea1-3e00-43aa-b648-f154be6b47ef */
   private String date;
   /** @pdOid 83eebbdb-4024-4ab9-8b66-19773a5bf4d7 */
   private String duration;
   
   /** @pdOid 6e3d7eb9-c5e1-472c-a295-fc4e150ec446
       @pdRoleInfo migr=yes name=OperationRoom assc=association18 mult=1..1 */
   public OperationRoom operationRoom;
   /** @pdOid d9b59309-357c-40ff-81bc-2dd2144cbcd0
       @pdRoleInfo migr=yes name=Doctor assc=association19 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   public java.util.List<Doctor> doctors;
   /** @pdOid add9696e-d5fb-4c39-ac58-b8ef2ea4eecc
       @pdRoleInfo migr=yes name=Patient assc=association20 mult=1..1 */
   public Patient patient;
   
   
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

}