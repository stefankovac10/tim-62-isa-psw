package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.ExaminationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface ExaminationRequestRepository extends JpaRepository<ExaminationRequest, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select er from ExaminationRequest er where er.id = (?1)")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="3000")})
    ExaminationRequest findByRequestId(Long id);

    List<ExaminationRequest> findByDoctorId(Long id);

    @Query("select er from ExaminationRequest er join er.examinationAppointment where er.clinicId = (?1)")
    Page<ExaminationRequest> findAllByClinic(Long clinicId, Pageable pageable);

    @Query("select er from ExaminationRequest er join fetch er.examinationAppointment where er.examinationAppointment.clinic = (?1)")
    List<ExaminationRequest> findByClinic(Clinic clinic);

}
