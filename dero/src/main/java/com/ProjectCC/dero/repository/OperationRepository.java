package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Operation;
import com.ProjectCC.dero.model.OperationRoom;
import com.ProjectCC.dero.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {


    List<Operation> findByDoctorsId(Long id);

    List<Operation> findByOperationRoom(OperationRoom operationRoom);

    List<Operation> findByPatient(Patient patient);

    @Query("select o from Operation o where o.patient.id = (?1)")
    List<Operation> findByPatientsID(Long patientsID);
}
