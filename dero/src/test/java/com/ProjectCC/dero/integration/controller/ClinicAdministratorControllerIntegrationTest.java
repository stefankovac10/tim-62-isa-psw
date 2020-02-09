package com.ProjectCC.dero.integration.controller;

import com.ProjectCC.dero.dto.ExaminationRequestDetailsDTO;
import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import com.ProjectCC.dero.model.UserTokenState;
import com.ProjectCC.dero.security.auth.JwtAuthenticationRequest;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClinicAdministratorControllerIntegrationTest {

    private static final String URL_PREFIX = "/api/cadmin";
    private static final String CLINIC_NOT_FOUND_URL = "/api/cadmin/scheduledExaminations/101/0";

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);


    private static final Long CLINIC_ID = 1L;
    private static final Long CLINIC_ID_NO_REQUESTS = 2L;
    private static final Long CLINIC_ID_DOES_NOT_EXIST = 101L;
    private static final int PAGE = 0;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String accessToken;


    @Before
    public void login() {
        ResponseEntity<UserTokenState> responseEntity = testRestTemplate.postForEntity("/api/auth/login", new JwtAuthenticationRequest("zika@gmail.com", "asdf"), UserTokenState.class);
        accessToken = "Bearer " + Objects.requireNonNull(responseEntity.getBody()).getToken();
    }

    @Test
    public void testGetExaminationsReturnsClinicNotFound() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);
        ResponseEntity<ExaminationRequestDetailsDTO[]> examRequests = this.testRestTemplate.exchange(CLINIC_NOT_FOUND_URL, HttpMethod.GET,  request, ExaminationRequestDetailsDTO[].class);

        assertEquals("Http status is bad request", HttpStatus.BAD_REQUEST, examRequests.getStatusCode());
        assertNull("Response does not contain body.", examRequests.getBody());
    }

    @Test
    public void testGetExaminationsReturnsListOfExaminationRequests() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);
        ResponseEntity<ExaminationRequestDetailsDTO[]> examRequests = this.testRestTemplate.exchange("/api/cadmin/scheduledExaminations/1/0", HttpMethod.GET,  request, ExaminationRequestDetailsDTO[].class);

        assertEquals("Http status is OK", HttpStatus.OK, examRequests.getStatusCode());
        assertNotNull("Response contains body.", examRequests.getBody());
    }

    @Test
    public void testReserveRoomReturnsStatusOk() {
        DateTime date = DateTime.parse("2020-02-02T15:43:00Z");
        ExaminationRoomDTO examinationRoomDTO = ExaminationRoomDTO.builder()
                .nextAvailable(date)
                .number(1)
                .name("Soba1")
                .requestId(1L).build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);
        ResponseEntity<Void> examRequests = this.testRestTemplate.exchange("/api/cadmin/reserve", HttpMethod.PUT, request, Void.class, examinationRoomDTO);

        assertEquals("Http status is Bad request", HttpStatus.BAD_REQUEST, examRequests.getStatusCode());
    }

    @Test
    public void testReserveRoomWithBadRequestIdReturnsBadRequest() {
        DateTime date = DateTime.parse("2020-02-02T15:43:00Z");
        ExaminationRoomDTO examinationRoomDTO = ExaminationRoomDTO.builder()
                .nextAvailable(date)
                .number(1)
                .name("Soba1")
                .requestId(101L).build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);
        ResponseEntity<Void> examRequests = this.testRestTemplate.exchange("/api/cadmin/reserve", HttpMethod.PUT, request, Void.class, examinationRoomDTO);

        assertEquals("Http status is not OK", HttpStatus.OK, examRequests.getStatusCode());
    }
}
