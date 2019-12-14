package com.ProjectCC.dero.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Examination {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "date", nullable = false)
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
           @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
           @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
   })
   private DateTime date;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "type_id", nullable = true)
   private TypeOfExamination type;

//   @Column(name = "duration", nullable = false)
//   private Period duration; // more changes needed probably

   @Column(name = "price", nullable = true)
   private String price;

   @Column(name = "discount", nullable = true)
   private String discount;

   @Column(name = "report", nullable = true)
   private String report;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "clinic_id", nullable = false)
   public Clinic clinic;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ER_id", nullable = true)
   public ExaminationRoom examinationRoom;

   //@OneToMany(mappedBy = "examination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
   public Diagnosis diagnosis;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "prescription_id", referencedColumnName = "id")
   public Prescription prescription;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "nurse_id", referencedColumnName = "id")
   public Nurse nurse;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "doctor_id", referencedColumnName = "id")
   public Doctor doctor;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "patient_id", referencedColumnName = "id")
   public Patient patient;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "medRec_id", nullable = true)
   public MedicalRecord medicalRecord;

   /*
   public Examination() {
   }

   public Examination(String date, String type, String duration, String price, String discount, String report, ExaminationRoom examinationRoom, Set<Diagnosis> diagnosis, Prescription prescription, Nurse nurse, Doctor doctor, Patient patient, MedicalRecord medicalRecord) {
      this.date = date;
      this.type = type;
      this.duration = duration;
      this.price = price;
      this.discount = discount;
      this.report = report;
      this.examinationRoom = examinationRoom;
      this.diagnosis = diagnosis;
      this.prescription = prescription;
      this.nurse = nurse;
      this.doctor = doctor;
      this.patient = patient;
      this.medicalRecord = medicalRecord;
   }

   public Examination(ExaminationDTO examinationDTO){
         this.date = examinationDTO.getDate();
         this.type = examinationDTO.getType();
         this.duration = examinationDTO.getDuration();
         this.price = examinationDTO.getPrice();
         this.discount = examinationDTO.getDiscount();
         this.report = examinationDTO.getReport();
         this.prescription = new Prescription(examinationDTO.getPrescription());
         this.nurse = new Nurse(examinationDTO.getNurse());
         this.doctor = new Doctor(examinationDTO.getDoctor());
         this.patient = (Patient) new User(examinationDTO.getPatient());
         this.medicalRecord =  new MedicalRecord(examinationDTO.getMedicalRecord());
   }
   */
}