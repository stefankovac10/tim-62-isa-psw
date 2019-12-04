package com.ProjectCC.dero.model;

import lombok.*;

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

   @Column(name = "date", nullable = false)
   private String startDate;

   @Column(name = "duration", nullable = false)
   private String endDate;

   @Column(name = "accepted")
   private boolean accepted;

   @OneToOne(mappedBy = "vacationRequest")
   public MedicalStaff medicalStaff;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "cadmin_id", nullable = false)
   private ClinicAdministrator administrator;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Clinic clinic;

//   public VacationRequest() {}

}