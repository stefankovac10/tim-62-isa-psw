package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query("update Diagnosis d set d.name=?1 , d.code =?2 , d.description =?3  where d.id=?4")
    void update(String name, String code, String description, Long id);

    Diagnosis findByName(String name);
}
