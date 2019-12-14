package com.ProjectCC.dero.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
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
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
           @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
           @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
   })
   private DateTime startDate;
//
   @Column(name = "end_date") //, columnDefinition = DbColumnConstants.DATE)
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
           @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
           @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
   })
   private DateTime endDate;

   @Column(name = "accepted")
   private boolean accepted;

   @OneToOne(mappedBy = "vacationRequest")
   public MedicalStaff medicalStaff;

   @ManyToOne(fetch = FetchType.LAZY)
   private Clinic clinic;

}