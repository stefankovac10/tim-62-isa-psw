package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.VacationRequest;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

    @Query("select vc from VacationRequest vc where vc.medicalStaff = (?1) and vc.startDate > (?2)")
    List<VacationRequest> findByDoc(Doctor doc, DateTime after);
}
