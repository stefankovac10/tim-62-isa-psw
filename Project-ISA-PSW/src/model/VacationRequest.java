/***********************************************************************
 * Module:  VacationRequest.java
 * Author:  Bax
 * Purpose: Defines the Class VacationRequest
 ***********************************************************************/

import java.util.*;

/** @pdOid 62432460-9562-4c9b-90c8-0580c5b92732 */
public class VacationRequest {
   /** @pdOid fabff403-34f7-41a5-bd81-6e34976b0a10 */
   private String date;
   /** @pdOid 270e0d7d-b534-4cf0-9a44-13c717b155ea */
   private String duration;
   
   /** @pdOid d9c8ccca-e015-4ef3-84f7-355c33d3d791
       @pdRoleInfo migr=yes name=MedicalStaff assc=association21 mult=1..1 */
   public MedicalStaff medicalStaff;

}