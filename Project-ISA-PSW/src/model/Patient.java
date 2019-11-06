/***********************************************************************
 * Module:  Patient.java
 * Author:  Bax
 * Purpose: Defines the Class Patient
 ***********************************************************************/

import java.util.*;

/** @pdOid f070b665-84e7-45b2-8332-2508d0f577aa */
public class Patient extends User {
   /** @pdOid 2c5b2d8b-a6a0-486a-8bcb-dc24406a66cf
       @pdRoleInfo migr=yes name=MedicalRecord assc=association15 mult=1..1 */
   public MedicalRecord medicalRecord;

}