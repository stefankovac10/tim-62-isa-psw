package com.ProjectCC.dero.integration.service;

import com.ProjectCC.dero.dto.ExaminationRequestDetailsDTO;
import com.ProjectCC.dero.exceptions.ClinicNotFoundException;
import com.ProjectCC.dero.service.ExaminationRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ExaminationRequestServiceIntegrationTest {

    @Autowired
    private ExaminationRequestService examinationRequestService;

    private static final Long CLINIC_ID = 1L;
    private static final Long CLINIC_ID_NO_REQUESTS = 2L;
    private static final Long CLINIC_ID__DOES_NOT_EXIST = 101L;
    private static final int PAGE = 0;
    private static final int NO_REQUESTS = 0;
    private static final int REQUESTS_SIZE = 0;

    @Test
    public void testGetAllWhenThereAreNoneRequests() {
        ResponseEntity<List<ExaminationRequestDetailsDTO>> responseEntity = this.examinationRequestService.getAll(CLINIC_ID_NO_REQUESTS, PAGE);

        List<ExaminationRequestDetailsDTO> dtos = responseEntity.getBody();
        assertThat(dtos).hasSize(NO_REQUESTS);
    }

    @Test
    public void testGetAll() {
        ResponseEntity<List<ExaminationRequestDetailsDTO>> responseEntity = this.examinationRequestService.getAll(CLINIC_ID, PAGE);

        List<ExaminationRequestDetailsDTO> dtos = responseEntity.getBody();
        assertThat(dtos).hasSize(REQUESTS_SIZE);
    }

    @Test(expected = ClinicNotFoundException.class)
    public void testGetAllNonExistingClinicId() {
        ResponseEntity<List<ExaminationRequestDetailsDTO>> responseEntity = this.examinationRequestService.getAll(CLINIC_ID__DOES_NOT_EXIST, PAGE);
    }
}
