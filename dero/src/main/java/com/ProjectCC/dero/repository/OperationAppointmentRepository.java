package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.ExaminationAppointment;
import com.ProjectCC.dero.model.ExaminationRoom;
import com.ProjectCC.dero.model.OperationAppointment;
import com.ProjectCC.dero.model.OperationRoom;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationAppointmentRepository extends JpaRepository<OperationAppointment, Long> {

    @Query("select oa from OperationAppointment oa where oa.operationRoom = (?1) and oa.startDate > (?2) ")
    List<OperationAppointment> findByRoomAndDate(OperationRoom operationRoom, DateTime startDate);

    List<OperationAppointment> findByOperationRoom(OperationRoom operationRoom);
}
