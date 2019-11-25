package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query("update Medication m set m.name=?1 , m.code =?2 , m.description =?3  where m.id=?4")
    void update(String name, String code, String description, Long id);

    Medication findByName(String name);
}
