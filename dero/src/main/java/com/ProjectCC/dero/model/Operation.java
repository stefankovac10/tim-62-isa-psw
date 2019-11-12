package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class Operation {
   private String date;
   private String duration;
   
   public OperationRoom operationRoom;

   public Operation() {
   }

   public Set<Doctor> doctors;
   public Patient patient;

}