package com.ProjectCC.dero.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date", nullable = false)
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
           @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
           @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
   })
   private DateTime date;

   @Column(name = "duration", nullable = true)
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDurationAsMillisLong")
   private Duration duration;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "medRec_id", nullable = false)
   public MedicalRecord medicalRecord;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "OR_id", nullable = false)
   public OperationRoom operationRoom;

   @ManyToMany
   @JoinTable(
           name = "operations_doctors",
           joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id")
   )
   public Set<Doctor> doctors;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "patient_id", nullable = false)
   public Patient patient;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "clinic_id", nullable = false)
   public Clinic clinic;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "appointment_id")
   private OperationAppointment operationAppointment;
}