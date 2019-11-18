package com.ProjectCC.dero.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class MedicalStaff extends User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "clinic_id", nullable = false)
   public Clinic clinic;

   @OneToOne()
   @JoinColumn(name = "vacReq_id", referencedColumnName = "id")
   private VacationRequest vacationRequest;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "clinicCenter_id", nullable = false)
   public ClinicCenter clinicCenter;

   @ManyToMany(mappedBy = "medicalStaffs")
   public Set<MedicalRecord> medicalRecords;

   @Autowired
   public MedicalStaff() {
   }

   @Autowired
   public MedicalStaff(Clinic clinic, VacationRequest vacationRequest) {
      this.clinic = clinic;
      this.vacationRequest = vacationRequest;
   }

   @Autowired
   public MedicalStaff(String firstName, String lastName, String jmbg, String password, String email,
                       String address, String city, String country, String telephone) {
      super(firstName, lastName, jmbg, password, email, address, city, country, telephone);
   }
}