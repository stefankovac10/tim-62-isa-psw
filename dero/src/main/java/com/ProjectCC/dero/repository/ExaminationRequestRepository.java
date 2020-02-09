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

    @Query("select er from ExaminationRequest er where er.examinationAppointment.clinic = (?1)")
    Page<ExaminationRequest> findAllByClinic(Clinic clinic, Pageable pageable);

    @Query("select er from ExaminationRequest er join fetch er.examinationAppointment where er.examinationAppointment.clinic = (?1)")
    List<ExaminationRequest> findByClinic(Clinic clinic);
}
