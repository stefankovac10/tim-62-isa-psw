package com.ProjectCC.dero.integration.service;

import com.ProjectCC.dero.dto.ExaminationRequestDetailsDTO;
import com.ProjectCC.dero.exceptions.ClinicNotFoundException;
import com.ProjectCC.dero.exceptions.ExaminationRequestNotFoundException;
import com.ProjectCC.dero.exceptions.ExaminationRoomNotFoundException;
import com.ProjectCC.dero.service.ExaminationRequestService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private static final int REQUESTS_SIZE = 4;

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
        this.examinationRequestService.getAll(CLINIC_ID__DOES_NOT_EXIST, PAGE);
    }

    @Test
    public void testReservingRoom() {
        DateTime date = DateTime.parse("2020-02-02T15:43:00Z");
        ResponseEntity<Void> responseEntity = this.examinationRequestService.reserve(1L, 1L, date);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test(expected = ExaminationRequestNotFoundException.class)
    public void testReservingRoomFromNonExistingRequest() {
        DateTime date = DateTime.parse("2020-02-02T15:43:00Z");
        this.examinationRequestService.reserve(101L, 1L, date);
    }

    @Test(expected = ExaminationRoomNotFoundException.class)
    public void testReservingRoomFromNonExistingExaminationRoom() {
        DateTime date = DateTime.parse("2020-02-02T15:43:00Z");
        this.examinationRequestService.reserve(1L, 101L, date);
    }

}
