package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Set;

@Getter
@Setter
public class Clinic {
   private String name;
   private String address;
   private String description;
   private String priceList;
   private Set<Examination> examinationSet;
   private ArrayList<Integer> marks;
   private double income;

   public Clinic() {}
}