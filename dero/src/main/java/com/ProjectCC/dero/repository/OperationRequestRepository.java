package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.OperationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRequestRepository extends JpaRepository<OperationRequest, Long> {
}
