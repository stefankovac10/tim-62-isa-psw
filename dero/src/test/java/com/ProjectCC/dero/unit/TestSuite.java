package com.ProjectCC.dero.unit;

import com.ProjectCC.dero.unit.controller.ClinicAdministratorControllerUnitTest;
import com.ProjectCC.dero.unit.repository.*;
import com.ProjectCC.dero.unit.service.ExaminationRequestServiceUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClinicRepositoryUnitTest.class, PatientRepositoryUnitTest.class, DoctorRepositoryUnitTest.class,
        ExaminationAppointmentRepositoryUnitTest.class, ExaminationRequestRepositoryUnitTest.class, ExaminationRoomRepositoryUnitTest.class,
        ExaminationRequestServiceUnitTest.class, ClinicAdministratorControllerUnitTest.class})
public class TestSuite {
}
