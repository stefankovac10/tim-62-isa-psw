package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Appointment;
import com.ProjectCC.dero.model.ExaminationAppointment;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
