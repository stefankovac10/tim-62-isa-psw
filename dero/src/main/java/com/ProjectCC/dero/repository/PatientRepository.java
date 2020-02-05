package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where lower(p.firstName) like lower(?1) and lower(p.lastName) like lower(?2) and lower(p.jmbg) like lower(?3)")
    Page<Patient> search(String firstName, String lastName, String jmbg, Pageable pageable);


    Patient findByEmail(String email);

    @Query("select p.examinations from Patient  p where p.id = ?1")
    List<Examination> getExamination(Long id);
}
