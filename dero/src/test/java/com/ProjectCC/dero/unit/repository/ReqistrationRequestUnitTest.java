package com.ProjectCC.dero.unit.repository;

import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.RegistrationRequestRepository;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class ReqistrationRequestUnitTest {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    private static final Long REQ_ID_EXISTS = 5L;
    private static final Long REQ_ID_NON_EXISTS = 55L;


    @Test
    public void ShouldFindRequest(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC,"firstName"));
        Page<RegistrationRequest> registrationRequests = registrationRequestRepository.findAll(pageable);

        assertNotNull("Found some request", registrationRequests);
    }

    @Test
    public void shouldNotFindRequestByID(){
        Optional<RegistrationRequest> registrationRequest = this.registrationRequestRepository.findById(REQ_ID_NON_EXISTS);

        assertFalse("Request is not present.", registrationRequest.isPresent());
    }

    @Test
    public void shoudFindRequestById(){
        Optional<RegistrationRequest> optionalReq = this.registrationRequestRepository.findById(REQ_ID_EXISTS);

        assertTrue("Request is present.", optionalReq.isPresent());
    }


    @Test
    public void deleteExistingRequest(){
        registrationRequestRepository.deleteById(REQ_ID_EXISTS);

        Optional<RegistrationRequest> optionalReq = this.registrationRequestRepository.findById(REQ_ID_EXISTS);

        assertFalse("Request is deleted.", optionalReq.isPresent());

    }


    @Test
    public void updateRequest(){
        Optional<RegistrationRequest> optionalReq = this.registrationRequestRepository.findById(REQ_ID_EXISTS);
        RegistrationRequest registrationRequest = optionalReq.get();
        registrationRequest.setVerified(true);

        this.registrationRequestRepository.save(registrationRequest);

        Optional<RegistrationRequest> optionalReq1 = this.registrationRequestRepository.findById(REQ_ID_EXISTS);
        RegistrationRequest registrationRequest1 = optionalReq1.get();

        assertTrue("Reqistration saved", registrationRequest1.isVerified());
        
    }
}
