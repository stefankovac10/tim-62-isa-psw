package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "clinic")
public class Clinic {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name", unique = true, nullable = false)
   private String name;

   @Column(name = "address", nullable = false)
   private String address;

   @Column(name = "description")
   private String description;

   @Column(name = "price_list")
   private String priceList;


//   private Set<Examination> examinationSet;
//   private ArrayList<Integer> marks;

   @Column(name = "income")
   private double income;

   public Clinic() {}

}