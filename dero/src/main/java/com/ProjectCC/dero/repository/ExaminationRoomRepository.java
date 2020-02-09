package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.ExaminationRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExaminationRoomRepository extends JpaRepository<ExaminationRoom, Long> {

    @Query("select er from ExaminationRoom er where lower(er.name) like lower(?1) and er.number = ?2")
    Page<ExaminationRoom> search(String sName, int number, Pageable pageable);

    @Query("select er from ExaminationRoom er where lower(er.name) like lower(?1)")
    Page<ExaminationRoom> searchName(String sName, Pageable pageable);

    @Query("select er from ExaminationRoom er where er.clinic = (?1)")
    List<ExaminationRoom> findByClinic(Clinic clinic);

    @Query("select er from ExaminationRoom er join fetch er.clinic where er.id = (?1)")
    Optional<ExaminationRoom> findByRoomId(Long roomId);

    @Query("select er from ExaminationRoom er where er.clinic.id = (?1)")
    Page<ExaminationRoom> findByClinicPage(Long clinicId, Pageable pageable);
}
