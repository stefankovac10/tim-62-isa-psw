package com.ProjectCC.dero.unit.service;

import com.ProjectCC.dero.dto.ExaminationRequestDetailsDTO;
import com.ProjectCC.dero.exceptions.ClinicNotFoundException;
import com.ProjectCC.dero.exceptions.UserNotFoundException;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.repository.ExaminationRequestRepository;
import com.ProjectCC.dero.repository.PatientRepository;
import com.ProjectCC.dero.service.ExaminationRequestService;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExaminationRequestServiceUnitTest {

    @Autowired
    private ExaminationRequestService examinationRequestService;

    @MockBean
    private ExaminationRequestRepository examinationRequestRepositoryMock;

    @MockBean
    private ClinicRepository clinicRepositoryMock;

    @MockBean
    private DoctorRepository doctorRepositoryMock;

    @MockBean
    private PatientRepository patientRepositoryMock;

    private static final Long CLINIC_ID_DOESNT_EXIT = 101L;
    private static final Long CLINIC_ID_WITH_NO_REQUESTS = 2L;
    private static final Long CLINIC_ID_EXISTS = 1L;
    private static final int PAGE = 0;
    private static final String CLINIC_ADDRESS_NR = "Adresa2";
    private static final String CLINIC_DESCRIPTION_NR = "opis2";
    private static final Double CLINIC_GRADE_NR = 2.9;
    private static final Double CLINIC_INCOME_NR = 2000D;
    private static final String CLINIC_NAME_NR = "Klinika2";
    private static final String CLINIC_ADDRESS = "Adresa1";
    private static final String CLINIC_DESCRIPTION = "opis1";
    private static final Double CLINIC_GRADE = 4.3;
    private static final Double CLINIC_INCOME = 20000D;
    private static final String CLINIC_NAME = "Klinika1";
    private static final Long DOCTOR_ID = 13L;
    private static final Long PATIENT_ID = 10L;
    private static final Long DOCTOR_ID_DOES_NOT_EXIST = 102L;
    private static final Long PATIENT_ID_DOES_NOT_EXIST = 103L;


    @Test(expected = ClinicNotFoundException.class)
    public void shouldReturnClinicNotFoundExceptionWhenClinicWithGivenIdDoesNotExist() {
        when(clinicRepositoryMock.findById(CLINIC_ID_DOESNT_EXIT)).thenReturn(Optional.empty());

        examinationRequestService.getAll(CLINIC_ID_DOESNT_EXIT, PAGE);
    }

    @Test
    public void shouldReturnResponseEntityWithEmptyDTOWhenThereAreNoRequests() {
        Clinic clinic = Clinic.builder()
                .name(CLINIC_NAME_NR)
                .address(CLINIC_ADDRESS_NR)
                .description(CLINIC_DESCRIPTION_NR)
                .income(CLINIC_INCOME_NR)
                .grade(CLINIC_GRADE_NR).build();
        Pageable pageable = PageRequest.of(PAGE, 10);
        when(clinicRepositoryMock.findById(CLINIC_ID_WITH_NO_REQUESTS)).thenReturn(Optional.of(clinic));
        when(examinationRequestRepositoryMock.findAllByClinic(clinic.getId(), pageable)).thenReturn(Page.empty());
        ResponseEntity<List<ExaminationRequestDetailsDTO>> responseEntity = examinationRequestService.getAll(CLINIC_ID_WITH_NO_REQUESTS, PAGE);

        verify(clinicRepositoryMock).findById(CLINIC_ID_WITH_NO_REQUESTS);
        verify(examinationRequestRepositoryMock).findAllByClinic(clinic.getId(), pageable);
        assertEquals("There are no Examination requests in this clinic.", 0, responseEntity.getBody().size());
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldReturnUserNotFoundExceptionWhenFindingNonExistingDoctorById() {
        Clinic clinic = Clinic.builder()
                .id(CLINIC_ID_EXISTS)
                .name(CLINIC_NAME)
                .address(CLINIC_ADDRESS)
                .description(CLINIC_DESCRIPTION)
                .income(CLINIC_INCOME)
                .grade(CLINIC_GRADE).build();
        Pageable pageable = PageRequest.of(PAGE, 10);
        List<ExaminationRequest> examinationRequests = new ArrayList<>();
        ExaminationAppointment examinationAppointment = ExaminationAppointment.builder()
                .clinic(clinic)
                .startDate(new DateTime("2020-10-24T15:43:00Z"))
                .duration(new Duration(600000)).build();
        ExaminationRequest examinationRequest = ExaminationRequest.builder()
                .clinicId(CLINIC_ID_EXISTS)
                .examinationAppointment(examinationAppointment)
                .doctorId(DOCTOR_ID_DOES_NOT_EXIST)
                .patientId(PATIENT_ID_DOES_NOT_EXIST).build();
        examinationRequests.add(examinationRequest);
        Page<ExaminationRequest> page = new PageImpl<>(examinationRequests);

        when(clinicRepositoryMock.findById(CLINIC_ID_EXISTS)).thenReturn(Optional.of(clinic));
        when(examinationRequestRepositoryMock.findAllByClinic(clinic.getId(), pageable)).thenReturn(page);
        when(doctorRepositoryMock.findById(DOCTOR_ID_DOES_NOT_EXIST)).thenReturn(Optional.empty());

        examinationRequestService.getAll(CLINIC_ID_EXISTS, PAGE);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldReturnUserNotFoundExceptionWhenFindingNonExistingPatientById() {
        Clinic clinic = Clinic.builder()
                .id(CLINIC_ID_EXISTS)
                .name(CLINIC_NAME)
                .address(CLINIC_ADDRESS)
                .description(CLINIC_DESCRIPTION)
                .income(CLINIC_INCOME)
                .grade(CLINIC_GRADE).build();
        Pageable pageable = PageRequest.of(PAGE, 10);
        List<ExaminationRequest> examinationRequests = new ArrayList<>();
        ExaminationAppointment examinationAppointment = ExaminationAppointment.builder()
                .clinic(clinic)
                .startDate(new DateTime("2020-10-24T15:43:00Z"))
                .duration(new Duration(600000)).build();
        ExaminationRequest examinationRequest = ExaminationRequest.builder()
                .clinicId(CLINIC_ID_EXISTS)
                .examinationAppointment(examinationAppointment)
                .doctorId(DOCTOR_ID)
                .patientId(PATIENT_ID_DOES_NOT_EXIST).build();
        examinationRequests.add(examinationRequest);
        Page<ExaminationRequest> page = new PageImpl<>(examinationRequests);
        Doctor doctor = Doctor.builder().id(DOCTOR_ID).build();

        when(clinicRepositoryMock.findById(CLINIC_ID_EXISTS)).thenReturn(Optional.of(clinic));
        when(examinationRequestRepositoryMock.findAllByClinic(clinic.getId(), pageable)).thenReturn(page);
        when(doctorRepositoryMock.findById(DOCTOR_ID)).thenReturn(Optional.of(doctor));

        examinationRequestService.getAll(CLINIC_ID_EXISTS, PAGE);
    }

    @Test
    public void shouldReturnResponseEntityWithExaminationRequestsFromGiverClinic() {
        Clinic clinic = Clinic.builder()
                .id(CLINIC_ID_EXISTS)
                .name(CLINIC_NAME)
                .address(CLINIC_ADDRESS)
                .description(CLINIC_DESCRIPTION)
                .income(CLINIC_INCOME)
                .grade(CLINIC_GRADE).build();
        Pageable pageable = PageRequest.of(PAGE, 10);
        List<ExaminationRequest> examinationRequests = new ArrayList<>();
        ExaminationAppointment examinationAppointment = ExaminationAppointment.builder()
                .clinic(clinic)
                .startDate(new DateTime("2020-10-24T15:43:00Z"))
                .duration(new Duration(600000)).build();
        ExaminationRequest examinationRequest = ExaminationRequest.builder()
                .clinicId(CLINIC_ID_EXISTS)
                .examinationAppointment(examinationAppointment)
                .doctorId(DOCTOR_ID)
                .patientId(PATIENT_ID).build();
        examinationRequests.add(examinationRequest);
        Page<ExaminationRequest> page = new PageImpl<>(examinationRequests);
        Doctor doctor = Doctor.builder().id(DOCTOR_ID).build();
        Patient patient = Patient.builder().id(PATIENT_ID).build();

        when(clinicRepositoryMock.findById(CLINIC_ID_EXISTS)).thenReturn(Optional.of(clinic));
        when(examinationRequestRepositoryMock.findAllByClinic(clinic.getId(), pageable)).thenReturn(page);
        when(doctorRepositoryMock.findById(DOCTOR_ID)).thenReturn(Optional.of(doctor));
        when(patientRepositoryMock.findById(PATIENT_ID)).thenReturn(Optional.of(patient));

        ResponseEntity<List<ExaminationRequestDetailsDTO>> eRooms = examinationRequestService.getAll(CLINIC_ID_EXISTS, PAGE);

        verify(clinicRepositoryMock).findById(CLINIC_ID_EXISTS);
        verify(examinationRequestRepositoryMock).findAllByClinic(clinic.getId(), pageable);
        verify(doctorRepositoryMock).findById(DOCTOR_ID);
        verify(patientRepositoryMock).findById(PATIENT_ID);

        assertEquals("List of examination request data transfer objects", 1, eRooms.getBody().size());
    }

}
