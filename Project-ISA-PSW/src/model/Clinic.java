import java.util.*;

public class Clinic {
   private String name;
   private String adress;
   private String description;
   private String priceList;
   private String listaTerminaSlobodnih;
   private String marks;
   private String income;
   
   public java.util.List<Doctor> doctors;
   public java.util.List<ClinicAdministrator> clinicAdministrators;
   public java.util.List<OperationRoom> operationRooms;
   public java.util.List<ExaminationRoom> examinationRooms;
   
   
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
   public java.util.List<ClinicAdministrator> getClinicAdministrators() {
      if (clinicAdministrators == null)
         clinicAdministrators = new java.util.ArrayList<ClinicAdministrator>();
      return clinicAdministrators;
   }
   
   public java.util.Iterator getIteratorClinicAdministrators() {
      if (clinicAdministrators == null)
         clinicAdministrators = new java.util.ArrayList<ClinicAdministrator>();
      return clinicAdministrators.iterator();
   }
   
   public void setClinicAdministrators(java.util.List<ClinicAdministrator> newClinicAdministrators) {
      removeAllClinicAdministrators();
      for (java.util.Iterator iter = newClinicAdministrators.iterator(); iter.hasNext();)
         addClinicAdministrators((ClinicAdministrator)iter.next());
   }
   
   public void addClinicAdministrators(ClinicAdministrator newClinicAdministrator) {
      if (newClinicAdministrator == null)
         return;
      if (this.clinicAdministrators == null)
         this.clinicAdministrators = new java.util.ArrayList<ClinicAdministrator>();
      if (!this.clinicAdministrators.contains(newClinicAdministrator))
      {
         this.clinicAdministrators.add(newClinicAdministrator);
         newClinicAdministrator.setClinic(this);      
      }
   }
   
   public void removeClinicAdministrators(ClinicAdministrator oldClinicAdministrator) {
      if (oldClinicAdministrator == null)
         return;
      if (this.clinicAdministrators != null)
         if (this.clinicAdministrators.contains(oldClinicAdministrator))
         {
            this.clinicAdministrators.remove(oldClinicAdministrator);
            oldClinicAdministrator.setClinic((Clinic)null);
         }
   }
   
   public void removeAllClinicAdministrators() {
      if (clinicAdministrators != null)
      {
         ClinicAdministrator oldClinicAdministrator;
         for (java.util.Iterator iter = getIteratorClinicAdministrators(); iter.hasNext();)
         {
            oldClinicAdministrator = (ClinicAdministrator)iter.next();
            iter.remove();
            oldClinicAdministrator.setClinic((Clinic)null);
         }
      }
   }
   public java.util.List<OperationRoom> getOperationRooms() {
      if (operationRooms == null)
         operationRooms = new java.util.ArrayList<OperationRoom>();
      return operationRooms;
   }
   
   public java.util.Iterator getIteratorOperationRooms() {
      if (operationRooms == null)
         operationRooms = new java.util.ArrayList<OperationRoom>();
      return operationRooms.iterator();
   }
   
   public void setOperationRooms(java.util.List<OperationRoom> newOperationRooms) {
      removeAllOperationRooms();
      for (java.util.Iterator iter = newOperationRooms.iterator(); iter.hasNext();)
         addOperationRooms((OperationRoom)iter.next());
   }
   
   public void addOperationRooms(OperationRoom newOperationRoom) {
      if (newOperationRoom == null)
         return;
      if (this.operationRooms == null)
         this.operationRooms = new java.util.ArrayList<OperationRoom>();
      if (!this.operationRooms.contains(newOperationRoom))
         this.operationRooms.add(newOperationRoom);
   }
   
   public void removeOperationRooms(OperationRoom oldOperationRoom) {
      if (oldOperationRoom == null)
         return;
      if (this.operationRooms != null)
         if (this.operationRooms.contains(oldOperationRoom))
            this.operationRooms.remove(oldOperationRoom);
   }
   
   public void removeAllOperationRooms() {
      if (operationRooms != null)
         operationRooms.clear();
   }
   public java.util.List<ExaminationRoom> getExaminationRooms() {
      if (examinationRooms == null)
         examinationRooms = new java.util.ArrayList<ExaminationRoom>();
      return examinationRooms;
   }
   
   public java.util.Iterator getIteratorExaminationRooms() {
      if (examinationRooms == null)
         examinationRooms = new java.util.ArrayList<ExaminationRoom>();
      return examinationRooms.iterator();
   }
   
   public void setExaminationRooms(java.util.List<ExaminationRoom> newExaminationRooms) {
      removeAllExaminationRooms();
      for (java.util.Iterator iter = newExaminationRooms.iterator(); iter.hasNext();)
         addExaminationRooms((ExaminationRoom)iter.next());
   }
   
   public void addExaminationRooms(ExaminationRoom newExaminationRoom) {
      if (newExaminationRoom == null)
         return;
      if (this.examinationRooms == null)
         this.examinationRooms = new java.util.ArrayList<ExaminationRoom>();
      if (!this.examinationRooms.contains(newExaminationRoom))
         this.examinationRooms.add(newExaminationRoom);
   }
   
   public void removeExaminationRooms(ExaminationRoom oldExaminationRoom) {
      if (oldExaminationRoom == null)
         return;
      if (this.examinationRooms != null)
         if (this.examinationRooms.contains(oldExaminationRoom))
            this.examinationRooms.remove(oldExaminationRoom);
   }
   
   public void removeAllExaminationRooms() {
      if (examinationRooms != null)
         examinationRooms.clear();
   }

}