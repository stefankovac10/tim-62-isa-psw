package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("from Prescription p where  p.examination.clinic.id = (?1)")
    List<Prescription> findAll(Long id);
}
