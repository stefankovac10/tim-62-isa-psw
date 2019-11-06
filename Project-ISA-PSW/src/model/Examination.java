import java.util.*;

public class Examination {
   private String date;
   private String type;
   private String duration;
   private String price;
   private String discount;
   private String report;
   
   public ExaminationRoom exeminationRoom;
   public java.util.List<Diagnosis> diagnosis;
   public Prescription prescription;
   public Doctor doctor;
   public Patient patient;
   public Nurse nurse;
   
   
   public java.util.List<Diagnosis> getDiagnosis() {
      if (diagnosis == null)
         diagnosis = new java.util.ArrayList<Diagnosis>();
      return diagnosis;
   }
   
   public java.util.Iterator getIteratorDiagnosis() {
      if (diagnosis == null)
         diagnosis = new java.util.ArrayList<Diagnosis>();
      return diagnosis.iterator();
   }
   
   public void setDiagnosis(java.util.List<Diagnosis> newDiagnosis) {
      removeAllDiagnosis();
      for (java.util.Iterator iter = newDiagnosis.iterator(); iter.hasNext();)
         addDiagnosis((Diagnosis)iter.next());
   }
   
   public void addDiagnosis(Diagnosis newDiagnosis) {
      if (newDiagnosis == null)
         return;
      if (this.diagnosis == null)
         this.diagnosis = new java.util.ArrayList<Diagnosis>();
      if (!this.diagnosis.contains(newDiagnosis))
         this.diagnosis.add(newDiagnosis);
   }
   
   public void removeDiagnosis(Diagnosis oldDiagnosis) {
      if (oldDiagnosis == null)
         return;
      if (this.diagnosis != null)
         if (this.diagnosis.contains(oldDiagnosis))
            this.diagnosis.remove(oldDiagnosis);
   }
   
   public void removeAllDiagnosis() {
      if (diagnosis != null)
         diagnosis.clear();
   }

}