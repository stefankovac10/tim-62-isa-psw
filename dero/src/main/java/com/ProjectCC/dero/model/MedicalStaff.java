package com.ProjectCC.dero.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
public class MedicalStaff extends User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "clinic_id", nullable = false)
   public Clinic clinic;

   @OneToMany(mappedBy = "medicalStaff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<VacationRequest> vacationRequest;

   @ManyToMany(mappedBy = "medicalStaffs")
   public Set<MedicalRecord> medicalRecords;

}