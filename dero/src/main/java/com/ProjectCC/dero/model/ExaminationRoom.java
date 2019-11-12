package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExaminationRoom {
   private int number;
   private String name;
   private int appointmentList;

   public ExaminationRoom() {
   }
}