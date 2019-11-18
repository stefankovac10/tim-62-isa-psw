package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class VacationRequest {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date", nullable = false)
   private String date;

   @Column(name = "duration", nullable = false)
   private String duration;

   @OneToOne(mappedBy = "vacationRequest")
   public MedicalStaff medicalStaff;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "cadmin_id", nullable = false)
   private ClinicAdministrator administrator;

   public VacationRequest() {
   }
}