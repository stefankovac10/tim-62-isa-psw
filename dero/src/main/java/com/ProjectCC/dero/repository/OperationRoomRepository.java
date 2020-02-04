package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.dto.OperationRoomDTO;
import com.ProjectCC.dero.model.OperationRoom;
import com.ProjectCC.dero.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OperationRoomRepository extends JpaRepository<OperationRoom, Long> {

    @Query("select opr from OperationRoom opr where lower(opr.name) like lower(?1) and opr.number = ?2")
    Page<OperationRoom> search(String sName, int number, Pageable pageable);

    @Query("select opr from OperationRoom opr where lower(opr.name) like lower(?1)")
    Page<OperationRoom> searchName(String sName, Pageable pageable);
}
