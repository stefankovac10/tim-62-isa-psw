package com.ProjectCC.dero.unit.controller;

import com.ProjectCC.dero.dto.ExaminationRequestDetailsDTO;
import com.ProjectCC.dero.exceptions.ClinicNotFoundException;
import com.ProjectCC.dero.model.UserTokenState;
import com.ProjectCC.dero.security.auth.JwtAuthenticationRequest;
import com.ProjectCC.dero.service.ExaminationRequestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClinicAdministratorControllerUnitTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ExaminationRequestService examinationRequestServiceMock;

    private String accessToken;
    private static final String GET_ALL_ENDPOINT = "/api/cadmin/scheduledExaminations/1/0";
    private static final String GET_ALL_ENDPOINT_CLINIC_WITH_NO_REQUESTS = "/api/cadmin/scheduledExaminations/2/0";
    private static final String GET_ALL_ENDPOINT_CLINIC_DOES_NOT_EXIST = "/api/cadmin/scheduledExaminations/101/0";
    private static final Long CLINIC_EXISTS_ID = 1L;
    private static final Long CLINIC_WITH_NO_REQUESTS_ID = 2L;
    private static final Long CLINIC_DOES_NOT_EXIST_ID = 101L;
    private static final int PAGE = 0;

    @Before
    public void login() {
        ResponseEntity<UserTokenState> responseEntity = testRestTemplate.postForEntity("/api/auth/login", new JwtAuthenticationRequest("zika@gmail.com", "asdf"), UserTokenState.class);
        accessToken = "Bearer " + Objects.requireNonNull(responseEntity.getBody()).getToken();
    }

    @Test
    public void shouldReturnBadRequestWhenClinicNotFoundExceptionIsThrown() {
        when(examinationRequestServiceMock.getAll(CLINIC_DOES_NOT_EXIST_ID, PAGE)).thenThrow(ClinicNotFoundException.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);
        ResponseEntity<ExaminationRequestDetailsDTO[]> examRequests = this.testRestTemplate.exchange(GET_ALL_ENDPOINT_CLINIC_DOES_NOT_EXIST, HttpMethod.GET,  request, ExaminationRequestDetailsDTO[].class);

        ExaminationRequestDetailsDTO[] list = examRequests.getBody();
        assertEquals("Http status is Bad request.", HttpStatus.BAD_REQUEST, examRequests.getStatusCode());
        assertNull("Response does not contain body.", list);
    }

    @Test
    public void shouldReturnEmptyListWhenClinicHasNoRequests() {
        List<ExaminationRequestDetailsDTO> requestsList = new ArrayList<>();
        ResponseEntity<List<ExaminationRequestDetailsDTO>> responseEntity = new ResponseEntity<>(requestsList, HttpStatus.OK);
        when(examinationRequestServiceMock.getAll(CLINIC_WITH_NO_REQUESTS_ID, PAGE)).thenReturn(responseEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);
        ResponseEntity<ExaminationRequestDetailsDTO[]> examRequests = this.testRestTemplate.exchange(GET_ALL_ENDPOINT_CLINIC_WITH_NO_REQUESTS, HttpMethod.GET,  request, ExaminationRequestDetailsDTO[].class);

        ExaminationRequestDetailsDTO[] list = examRequests.getBody();
        assertEquals("Http status is Bad request.", HttpStatus.OK, examRequests.getStatusCode());
        assertNotNull("Response is not null", list);
        assertEquals("Examination requests list is empty.", 0, list.length);
    }

}
