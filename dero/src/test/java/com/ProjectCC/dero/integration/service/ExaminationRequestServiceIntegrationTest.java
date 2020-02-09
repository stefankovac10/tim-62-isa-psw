package com.ProjectCC.dero.integration.service;

import com.ProjectCC.dero.service.ExaminationRequestService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ExaminationRequestServiceIntegrationTest {

    @Autowired
    private ExaminationRequestService examinationRequestService;

    
}
