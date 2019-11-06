import java.util.*;

public class Prescription {
   public Doctor doctor;
   public Nurse nurse;
   
   public java.util.List<Medication> medication;
   
   
   public java.util.List<Medication> getMedication() {
      if (medication == null)
         medication = new java.util.ArrayList<Medication>();
      return medication;
   }
   
   public java.util.Iterator getIteratorMedication() {
      if (medication == null)
         medication = new java.util.ArrayList<Medication>();
      return medication.iterator();
   }
   
   public void setMedication(java.util.List<Medication> newMedication) {
      removeAllMedication();
      for (java.util.Iterator iter = newMedication.iterator(); iter.hasNext();)
         addMedication((Medication)iter.next());
   }
   
   public void addMedication(Medication newMedication) {
      if (newMedication == null)
         return;
      if (this.medication == null)
         this.medication = new java.util.ArrayList<Medication>();
      if (!this.medication.contains(newMedication))
         this.medication.add(newMedication);
   }
   
   public void removeMedication(Medication oldMedication) {
      if (oldMedication == null)
         return;
      if (this.medication != null)
         if (this.medication.contains(oldMedication))
            this.medication.remove(oldMedication);
   }
   
   public void removeAllMedication() {
      if (medication != null)
         medication.clear();
   }

}