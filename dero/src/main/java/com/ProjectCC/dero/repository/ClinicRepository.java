package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long>{

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query("update Clinic c set c.name=?1 , c.address =?2 , c.description =?3  where c.id=?4")
    void update(String name, String address, String description, Long id);

    Clinic findByName(String name);

    /*
    @Query("from Clinic c where c.name = ?1 and c.address = ?2 and c.description = ?3")
    List<Clinic> searchClinics(String name, String address, String description);
    */

    @Query("from Clinic c where lower(c.name) like lower(?1) and lower(c.address) like lower(?2) and lower(c.description) like lower(?3)")
    List<Clinic> searchClinics(String name, String address, String description);
}
