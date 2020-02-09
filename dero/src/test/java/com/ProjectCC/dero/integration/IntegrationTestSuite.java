package com.ProjectCC.dero.integration;

import com.ProjectCC.dero.integration.controller.ClinicAdministratorControllerIntegrationTest;
import com.ProjectCC.dero.integration.service.ExaminationRequestServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClinicAdministratorControllerIntegrationTest.class, ExaminationRequestServiceIntegrationTest.class})
public class IntegrationTestSuite {
}
