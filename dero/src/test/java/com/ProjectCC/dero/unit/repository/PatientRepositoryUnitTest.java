package com.ProjectCC.dero.unit.repository;

import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.repository.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class PatientRepositoryUnitTest {

    @Autowired
    private PatientRepository patientRepository;

    private static final Long PATIENT_ID_EXISTS = 10L;
    private static final Long PATIENT_ID_DOESNT_EXIST = 102L;

    @Test
    public void shouldReturnEmptyOptionalWhenFindingNonExistingPatientId() {
        Optional<Patient> optionalPatient = this.patientRepository.findById(PATIENT_ID_DOESNT_EXIST);

        assertFalse("Patient is not present.", optionalPatient.isPresent());
    }

    @Test
    public void shouldReturnPatientWhenFindingExistingPatientId() {
        Optional<Patient> optionalPatient = this.patientRepository.findById(PATIENT_ID_EXISTS);

        assertTrue("Patient is not present.", optionalPatient.isPresent());
    }
}
