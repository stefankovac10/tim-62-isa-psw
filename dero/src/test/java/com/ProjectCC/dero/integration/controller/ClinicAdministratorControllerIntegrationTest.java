package com.ProjectCC.dero.integration.controller;

import com.ProjectCC.dero.dto.ExaminationRequestDetailsDTO;
import com.ProjectCC.dero.exceptions.ClinicNotFoundException;
import com.ProjectCC.dero.model.UserTokenState;
import com.ProjectCC.dero.security.auth.JwtAuthenticationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
