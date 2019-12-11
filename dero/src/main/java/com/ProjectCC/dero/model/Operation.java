package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Period;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Operation {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date", nullable = false)
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate", parameters = {
           @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
           @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
   })
   private DateTime date;

   @Column(name = "duration", nullable = false)
   private Period duration; // more changes needed probably

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

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "patient_id", nullable = false)
   public Patient patient;

   public Operation() {
   }

}