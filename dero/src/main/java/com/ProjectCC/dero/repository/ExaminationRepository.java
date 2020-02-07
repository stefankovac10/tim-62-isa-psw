package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.*;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    @Query("select e from Examination e where e.doctor.id = (?1)")
    List<Examination> findDocExamination(Long id);

    @Query("select e from Examination e where e.nurse.id = (?1)")
    List<Examination> findNurseExamination(Long id);

    List<Examination> findByType(TypeOfExamination typeOfExamination);

    List<Examination> findByExaminationRoom(ExaminationRoom examinationRoom);

    List<Examination> findByDoctor(Doctor doc);

    List<Examination> findByNurse(Nurse nurse);
}
