package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.OperationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRequestRepository extends JpaRepository<OperationRequest, Long> {


    @Query("select o from OperationRequest o where o.clinic.id =?1")
    Page<OperationRequest> findByClinic(Long id, Pageable pageable);

    @Query("select o from OperationRequest o where o.clinic =?1")
    List<OperationRequest> findByClinicAll(Clinic clinic);
}
