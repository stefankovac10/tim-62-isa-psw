/***********************************************************************
 * Module:  MedicalStaff.java
 * Author:  Bax
 * Purpose: Defines the Class MedicalStaff
 ***********************************************************************/

import java.util.*;

/** @pdOid 39dafa2f-f670-4628-b1ea-f981129d05c8 */
public class MedicalStaff extends User {
   /** @pdOid 8d2685e3-4f24-4904-9717-cdba932c6ab9
       @pdRoleInfo migr=yes name=Clinic assc=association4 mult=0..1 */
   public Clinic clinic;

}