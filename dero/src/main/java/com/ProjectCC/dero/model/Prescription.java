package com.ProjectCC.dero.model;

import com.ProjectCC.dero.dto.ExaminationDTO;
import com.ProjectCC.dero.dto.MedicationDTO;
import com.ProjectCC.dero.dto.PrescriptionDTO;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "certified")
   private Boolean certified;

   @OneToOne(mappedBy = "prescription")
   public Examination examination;

   @ManyToMany
   @JoinTable(
           name = "prescriptions_medication",
           joinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id")
   )
   public Set<Medication> medication;

   @OneToOne()
   @JoinColumn(name = "doctor_id", referencedColumnName = "id")
   public Doctor doctor;

   @OneToOne()
   @JoinColumn(name = "nurse_id", referencedColumnName = "id")
   public Nurse nurse;

   /*
   @Autowired
   public Prescription() {
   }

   @Autowired
   public Prescription(Long id,Boolean certified, Examination examination, Set<Medication> medication, Doctor doctor, Nurse nurse) {
      this.id = id;
      this.certified = certified;
      this.examination = examination;
      this.medication = medication;
      this.doctor = doctor;
      this.nurse = nurse;
   }

   @Autowired
   public Prescription(PrescriptionDTO prescriptionDTO){
      this.id = prescriptionDTO.getId();
      this.certified = prescriptionDTO.getCertified();
      this.doctor = new Doctor(prescriptionDTO.getDoctor());
      this.nurse = new Nurse(prescriptionDTO.getNurse());
      for(MedicationDTO med: prescriptionDTO.getMedications()){
         this.medication.add(new Medication(med));
      }
   }*/
}