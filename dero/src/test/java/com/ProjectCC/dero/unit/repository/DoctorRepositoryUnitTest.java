package com.ProjectCC.dero.unit.repository;

import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.repository.DoctorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class DoctorRepositoryUnitTest {

    @Autowired
    private DoctorRepository doctorRepository;

    private static final Long DOC_ID_EXITS = 13L;
    private static final Long DOC_ID_DOESNT_EXIT = 101L;

    @Test
    public void shouldReturnEmptyOptionalWhenFindingNonExistingDoctorId() {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(DOC_ID_DOESNT_EXIT);

        assertFalse("Doctor is not present", optionalDoctor.isPresent());
    }

    @Test
    public void shouldReturnDoctorWhenFindingExistingDoctorById() {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(DOC_ID_EXITS);

        assertTrue("Doctor is present.", optionalDoctor.isPresent());
    }

}
