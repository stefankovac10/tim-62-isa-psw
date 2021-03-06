package com.ProjectCC.dero.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends MedicalStaff {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "grade")
   private Double grade;

   @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Examination> examinations;

   @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   public Set<Prescription> prescriptions;

   /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "medRec_id", nullable = false)
   private MedicalRecord medicalRecord;*/

   @ManyToMany(mappedBy = "doctors" , fetch = FetchType.LAZY, cascade = CascadeType.ALL )
   public Set<Operation> operations;

   @ManyToOne
   @JoinColumn(name = "specialisedType_id", nullable = true)
   private TypeOfExamination specialisedType;


}