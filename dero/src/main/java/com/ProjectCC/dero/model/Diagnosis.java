package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Diagnosis {
   private String name;
   private String description;
   private String code;

   public Diagnosis() {
   }
}