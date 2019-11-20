package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

   @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<ClinicAdministrator> administrators;

   @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
   private Set<MedicalStaff> medicalStaffs;

//   @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
//   public Set<MedicalStaff> medicalStaff;

   //private Set<Examination> examinationSet;
   //private ArrayList<Integer> marks;

   @Column(name = "income")
   private double income;

   @Autowired
   public Clinic() {}

}