import java.util.*;

public class MedicalRecord {
   private String height;
   private String weight;
   private String bloodType;
   private String allergies;
   private String diopter;
   
   public java.util.List<Nurse> nurses;
   public java.util.List<Doctor> doctors;
   
   
   public java.util.List<Nurse> getNurses() {
      if (nurses == null)
         nurses = new java.util.ArrayList<Nurse>();
      return nurses;
   }
   
   public java.util.Iterator getIteratorNurses() {
      if (nurses == null)
         nurses = new java.util.ArrayList<Nurse>();
      return nurses.iterator();
   }
   
   public void setNurses(java.util.List<Nurse> newNurses) {
      removeAllNurses();
      for (java.util.Iterator iter = newNurses.iterator(); iter.hasNext();)
         addNurses((Nurse)iter.next());
   }
   
   public void addNurses(Nurse newNurse) {
      if (newNurse == null)
         return;
      if (this.nurses == null)
         this.nurses = new java.util.ArrayList<Nurse>();
      if (!this.nurses.contains(newNurse))
         this.nurses.add(newNurse);
   }
   
   public void removeNurses(Nurse oldNurse) {
      if (oldNurse == null)
         return;
      if (this.nurses != null)
         if (this.nurses.contains(oldNurse))
            this.nurses.remove(oldNurse);
   }
   
   public void removeAllNurses() {
      if (nurses != null)
         nurses.clear();
   }
   public java.util.List<Doctor> getDoctors() {
      if (doctors == null)
         doctors = new java.util.ArrayList<Doctor>();
      return doctors;
   }
   
   public java.util.Iterator getIteratorDoctors() {
      if (doctors == null)
         doctors = new java.util.ArrayList<Doctor>();
      return doctors.iterator();
   }
   
   public void setDoctors(java.util.List<Doctor> newDoctors) {
      removeAllDoctors();
      for (java.util.Iterator iter = newDoctors.iterator(); iter.hasNext();)
         addDoctors((Doctor)iter.next());
   }
   
   public void addDoctors(Doctor newDoctor) {
      if (newDoctor == null)
         return;
      if (this.doctors == null)
         this.doctors = new java.util.ArrayList<Doctor>();
      if (!this.doctors.contains(newDoctor))
         this.doctors.add(newDoctor);
   }
   
   public void removeDoctors(Doctor oldDoctor) {
      if (oldDoctor == null)
         return;
      if (this.doctors != null)
         if (this.doctors.contains(oldDoctor))
            this.doctors.remove(oldDoctor);
   }
   
   public void removeAllDoctors() {
      if (doctors != null)
         doctors.clear();
   }

}