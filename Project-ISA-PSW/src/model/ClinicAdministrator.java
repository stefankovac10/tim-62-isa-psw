/***********************************************************************
 * Module:  ClinicAdministrator.java
 * Author:  Bax
 * Purpose: Defines the Class ClinicAdministrator
 ***********************************************************************/

import java.util.*;

/** @pdOid 3798df17-0bb9-4495-ad33-f5b7c640743b */
public class ClinicAdministrator extends User {
   /** @pdOid 01b9359c-7134-4f0a-861e-d60ec9b2a7c0
       @pdRoleInfo migr=yes name=Clinic assc=association1 mult=1..1 */
   public Clinic clinic;
   /** @pdOid 8d90009c-08ee-49f7-90ef-fa23c88abd38
       @pdRoleInfo migr=yes name=VacationRequest assc=association23 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public java.util.List<VacationRequest> vacationRequests;
   
   
   /** @pdGenerated default getter */
   public java.util.List<VacationRequest> getVacationRequests() {
      if (vacationRequests == null)
         vacationRequests = new java.util.ArrayList<VacationRequest>();
      return vacationRequests;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorVacationRequests() {
      if (vacationRequests == null)
         vacationRequests = new java.util.ArrayList<VacationRequest>();
      return vacationRequests.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newVacationRequests */
   public void setVacationRequests(java.util.List<VacationRequest> newVacationRequests) {
      removeAllVacationRequests();
      for (java.util.Iterator iter = newVacationRequests.iterator(); iter.hasNext();)
         addVacationRequests((VacationRequest)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newVacationRequest */
   public void addVacationRequests(VacationRequest newVacationRequest) {
      if (newVacationRequest == null)
         return;
      if (this.vacationRequests == null)
         this.vacationRequests = new java.util.ArrayList<VacationRequest>();
      if (!this.vacationRequests.contains(newVacationRequest))
         this.vacationRequests.add(newVacationRequest);
   }
   
   /** @pdGenerated default remove
     * @param oldVacationRequest */
   public void removeVacationRequests(VacationRequest oldVacationRequest) {
      if (oldVacationRequest == null)
         return;
      if (this.vacationRequests != null)
         if (this.vacationRequests.contains(oldVacationRequest))
            this.vacationRequests.remove(oldVacationRequest);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllVacationRequests() {
      if (vacationRequests != null)
         vacationRequests.clear();
   }

}