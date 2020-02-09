package com.ProjectCC.dero.unit.service;


import com.ProjectCC.dero.dto.OperationRequestDTO;
import com.ProjectCC.dero.model.OperationRequest;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.repository.OperationRequestRepository;
import com.ProjectCC.dero.repository.RegistrationRequestRepository;
import com.ProjectCC.dero.service.OperationRequestService;
import com.ProjectCC.dero.service.RegistrationRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OperationRequestServiceUnitTest {

    @Autowired
    OperationRequestService operationRequestService;

    @MockBean
    OperationRequestRepository operationRequestRepositoryMock;




}
