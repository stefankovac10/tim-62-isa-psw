package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.ExaminationAppointment;
import com.ProjectCC.dero.model.ExaminationRoom;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExaminationAppointmentRepository extends JpaRepository<ExaminationAppointment, Long> {

    @Query("select ea from ExaminationAppointment ea where ea.examinationRoom = (?1) and ea.startDate > (?2) ")
    List<ExaminationAppointment> findByRoomAndDate(ExaminationRoom examinationRoom, DateTime startDate);

    List<ExaminationAppointment> findByExaminationRoom(ExaminationRoom examinationRoom);
}
