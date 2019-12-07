package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.ClinicDTO;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

   @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<MedicalStaff> medicalStaff;

   @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<VacationRequest> vacationRequests;

   @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Room> rooms;
   
   //private Set<Examination> examinationSet;
   //private ArrayList<Integer> marks;

   @Column(name = "income")
   private Double income;

   @Column(name = "grade")
   private Double grade;

//   @Autowired
//   public Clinic() {}
//
//   public Clinic(ClinicDTO clinicDTO){
//      this.name = clinicDTO.getName();
//      this.id = clinicDTO.getId();
//      this.description = clinicDTO.getDescription();
//      this.address = clinicDTO.getAddress();
//      this.income  = clinicDTO.getIncome();
//   }
}