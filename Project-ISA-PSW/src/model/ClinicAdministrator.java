import java.util.*;

public class ClinicAdministrator extends User {
   public Clinic clinic;
   
   
   public Clinic getClinic() {
      return clinic;
   }
   
   public void setClinic(Clinic newClinic) {
      if (this.clinic == null || !this.clinic.equals(newClinic))
      {
         if (this.clinic != null)
         {
            Clinic oldClinic = this.clinic;
            this.clinic = null;
            oldClinic.removeClinicAdministrators(this);
         }
         if (newClinic != null)
         {
            this.clinic = newClinic;
            this.clinic.addClinicAdministrators(this);
         }
      }
   }

}