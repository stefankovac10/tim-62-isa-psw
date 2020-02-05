package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.ExaminationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationRequestRepository extends JpaRepository<ExaminationRequest, Long> {

    List<ExaminationRequest> findByDoctorId(Long id);
}
