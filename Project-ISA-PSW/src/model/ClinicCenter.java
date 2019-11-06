import java.util.*;

public class ClinicCenter {
   public java.util.List<Clinic> clinics;
   public java.util.List<ClinicCenterAdministrator> admins;
   
   
   public java.util.List<Clinic> getClinics() {
      if (clinics == null)
         clinics = new java.util.ArrayList<Clinic>();
      return clinics;
   }
   
   public java.util.Iterator getIteratorClinics() {
      if (clinics == null)
         clinics = new java.util.ArrayList<Clinic>();
      return clinics.iterator();
   }
   
   public void setClinics(java.util.List<Clinic> newClinics) {
      removeAllClinics();
      for (java.util.Iterator iter = newClinics.iterator(); iter.hasNext();)
         addClinics((Clinic)iter.next());
   }
   
   public void addClinics(Clinic newClinic) {
      if (newClinic == null)
         return;
      if (this.clinics == null)
         this.clinics = new java.util.ArrayList<Clinic>();
      if (!this.clinics.contains(newClinic))
         this.clinics.add(newClinic);
   }
   
   public void removeClinics(Clinic oldClinic) {
      if (oldClinic == null)
         return;
      if (this.clinics != null)
         if (this.clinics.contains(oldClinic))
            this.clinics.remove(oldClinic);
   }
   
   public void removeAllClinics() {
      if (clinics != null)
         clinics.clear();
   }
   public java.util.List<ClinicCenterAdministrator> getAdmins() {
      if (admins == null)
         admins = new java.util.ArrayList<ClinicCenterAdministrator>();
      return admins;
   }
   
   public java.util.Iterator getIteratorAdmins() {
      if (admins == null)
         admins = new java.util.ArrayList<ClinicCenterAdministrator>();
      return admins.iterator();
   }
   
   public void setAdmins(java.util.List<ClinicCenterAdministrator> newAdmins) {
      removeAllAdmins();
      for (java.util.Iterator iter = newAdmins.iterator(); iter.hasNext();)
         addAdmins((ClinicCenterAdministrator)iter.next());
   }
   
   public void addAdmins(ClinicCenterAdministrator newClinicCenterAdministrator) {
      if (newClinicCenterAdministrator == null)
         return;
      if (this.admins == null)
         this.admins = new java.util.ArrayList<ClinicCenterAdministrator>();
      if (!this.admins.contains(newClinicCenterAdministrator))
         this.admins.add(newClinicCenterAdministrator);
   }
   
   public void removeAdmins(ClinicCenterAdministrator oldClinicCenterAdministrator) {
      if (oldClinicCenterAdministrator == null)
         return;
      if (this.admins != null)
         if (this.admins.contains(oldClinicCenterAdministrator))
            this.admins.remove(oldClinicCenterAdministrator);
   }
   
   public void removeAllAdmins() {
      if (admins != null)
         admins.clear();
   }

}