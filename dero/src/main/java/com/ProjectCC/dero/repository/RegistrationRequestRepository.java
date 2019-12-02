package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query("update RegistrationRequest r set r.verified=?2   where r.id=?1")
    void update(Long id, boolean verified);

}
