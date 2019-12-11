package com.ProjectCC.dero.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequest {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "start_date") //, columnDefinition = DbColumnConstants.DATE)
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate", parameters = {
           @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
           @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
   })
   private LocalDate startDate;
//
   @Column(name = "end_date") //, columnDefinition = DbColumnConstants.DATE)
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate", parameters = {
           @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
           @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
   })
   private LocalDate endDate;

//   @Column(name = "start_date")
//   private String startDate;
//   @Column(name = "end_date")
//   private String endDate;

   @Column(name = "accepted")
   private boolean accepted;

   @OneToOne(mappedBy = "vacationRequest")
   public MedicalStaff medicalStaff;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Clinic clinic;

//   public VacationRequest() {}

}