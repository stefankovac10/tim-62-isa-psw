package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Operation;
import com.ProjectCC.dero.model.OperationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {


    List<Operation> findByDoctorsId(Long id);

    List<Operation> findByOperationRoom(OperationRoom operationRoom);

}
