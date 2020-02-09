package com.ProjectCC.dero.unit.repository;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.ExaminationRequest;
import com.ProjectCC.dero.repository.ExaminationRequestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class ExaminationRequestRepositoryUnitTest {

    @Autowired
    private ExaminationRequestRepository examinationRequestRepository;

    private static final Long CLINIC_ID_DOES_NOT_EXIST = 42L;
    private static final Long CLINIC_ID_EXIST = 1L;
    private static final String CLINIC_ADDRESS = "Adresaq";
    private static final String CLINIC_DESCRIPTION = "opis1";
    private static final Double CLINIC_GRADE = 4.3;
    private static final Double CLINIC_INCOME = 20000D;
    private static final String CLINIC_NAME = "Sveti vid";
    private static final Long EXAM_REQUEST_ID_EXISTS = 1L;
    private static final Long EXAM_REQUEST_ID_DOESNT_EXIST = 102L;


    @Test
    public void shouldReturnEmptyPageWhenClinicHasNoRequests() {
        Clinic clinic = Clinic.builder().id(CLINIC_ID_DOES_NOT_EXIST)
                .name(CLINIC_NAME)
                .address(CLINIC_ADDRESS)
                .description(CLINIC_DESCRIPTION)
                .grade(CLINIC_GRADE)
                .income(CLINIC_INCOME).build();
        Pageable pageable = PageRequest.of(0, 10);
        Page<ExaminationRequest> examinationRequests = this.examinationRequestRepository.findAllByClinic(clinic.getId(), pageable);

        assertEquals("There are no requests from this clinic.", 0, examinationRequests.getNumberOfElements());
    }

    @Test
    public void shouldReturnPageWithExaminationRequestsWhenClinicHasRequests() {
        Clinic clinic = Clinic.builder().id(CLINIC_ID_EXIST)
                .name(CLINIC_NAME)
                .address(CLINIC_ADDRESS)
                .description(CLINIC_DESCRIPTION)
                .grade(CLINIC_GRADE)
                .income(CLINIC_INCOME).build();
        Pageable pageable = PageRequest.of(0, 10);
        Page<ExaminationRequest> examinationRequests = this.examinationRequestRepository.findAllByClinic(clinic.getId(), pageable);

        assertTrue("There are examination requests in this clinic.",examinationRequests.getNumberOfElements() > 0);
    }

    @Test
    public void shouldReturnEmptyOptionalWhenFindingNonExistingExaminationRequestById() {
        Optional<ExaminationRequest> optionalExaminationRequest = this.examinationRequestRepository.findById(EXAM_REQUEST_ID_DOESNT_EXIST);

        assertFalse("Examination request is not present.", optionalExaminationRequest.isPresent());
    }

    @Test
    public void shouldReturnExaminationRequestWhenFindingExaminationRequestById() {
        Optional<ExaminationRequest> optionalExaminationRequest = this.examinationRequestRepository.findById(EXAM_REQUEST_ID_EXISTS);

        assertTrue("Examination request is present.", optionalExaminationRequest.isPresent());
    }

}
