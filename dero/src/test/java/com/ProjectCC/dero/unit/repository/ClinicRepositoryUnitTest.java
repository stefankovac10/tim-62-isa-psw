package com.ProjectCC.dero.unit.repository;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.repository.ClinicRepository;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class ClinicRepositoryUnitTest {

    @Autowired
    private ClinicRepository clinicRepository;

    private static final Long CLINIC_ID_DOES_NOT_EXIST = 42L;
    private static final Long CLINIC_ID_EXIST = 1L;
    private static final String CLINIC_ADDRESS = "Adresaq";
    private static final String CLINIC_DESCRIPTION = "opis1";
    private static final Double CLINIC_GRADE = 4.3;
    private static final Double CLINIC_INCOME = 2000D;
    private static final String CLINIC_NAME = "Klinika1";

    @Test
    public void shouldReturnEmptyOptionalWhenFindingNonExistingClinicByClinicId() {
        Optional<Clinic> optionalClinic = this.clinicRepository.findById(CLINIC_ID_DOES_NOT_EXIST);

        assertFalse("Clinic is not present.", optionalClinic.isPresent());
    }

    @Test
    public void shouldReturnClinicWhenFindingExistingClinicById() {
        Optional<Clinic> optionalClinic = this.clinicRepository.findById(CLINIC_ID_EXIST);

        assertTrue("Clinic is present.", optionalClinic.isPresent());
        assertCorrectClinicReturned(optionalClinic.get());
    }

    private void assertCorrectClinicReturned(Clinic clinic) {
        assertEquals("Clinic contains correct name", clinic.getName(), CLINIC_NAME);
        assertEquals("Clinic contains correct description", clinic.getDescription(), CLINIC_DESCRIPTION);
        assertEquals("Clinic contains correct address", clinic.getAddress(), CLINIC_ADDRESS);
        assertEquals("Clinic contains correct income", clinic.getIncome(), CLINIC_INCOME);
        assertEquals("Clinic contains correct grade", clinic.getGrade(), CLINIC_GRADE);
    }
}

