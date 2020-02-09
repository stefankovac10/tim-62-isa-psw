package com.ProjectCC.dero.model;

import lombok.*;

import javax.persistence.*;

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

   @Version
   private int version;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "appointment_id")
   private ExaminationAppointment examinationAppointment;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "type_id", nullable = true)
   private TypeOfExamination type;

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
   @JoinColumn(name = "er_id", nullable = true)
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

}