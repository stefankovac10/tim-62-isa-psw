import java.util.*;

public class Operation {
   private Date date;
   private String duration;
   
   public OperationRoom operationRoom;
   public java.util.List<Doctor> doctors;
   public Patient patient;
   
   
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