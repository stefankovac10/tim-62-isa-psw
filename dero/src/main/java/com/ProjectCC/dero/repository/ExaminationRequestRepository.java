package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.ExaminationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExaminationRequestRepository extends JpaRepository<ExaminationRequest, Long> {

    List<ExaminationRequest> findByDoctorId(Long id);

    @Query("select er from ExaminationRequest er join er.examinationAppointment.examinationRoom where er.clinicId = (?1)")
    Page<ExaminationRequest> findAllByClinic(Long clinicId, Pageable pageable);
}
