package com.ProjectCC.dero.unit.repository;

import com.ProjectCC.dero.model.ExaminationRoom;
import com.ProjectCC.dero.repository.ExaminationRoomRepository;
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
public class ExaminationRoomRepositoryUnitTest {

    @Autowired
    private ExaminationRoomRepository examinationRoomRepository;

    private static final Long EXAM_ROOM_ID_EXISTS = 1L;
    private static final Long EXAM_ROOM_ID_DOESNT_EXIST = 103L;

    @Test
    public void shouldReturnEmptyOptionalWhenFindingNonExistingRoomId() {
        Optional<ExaminationRoom> optionalExaminationRoom = this.examinationRoomRepository.findById(EXAM_ROOM_ID_DOESNT_EXIST);

        assertFalse("Examination room is not present", optionalExaminationRoom.isPresent());
    }

    @Test
    public void shouldReturnExaminationRoomWhenFindingExaminationRoomById() {
        Optional<ExaminationRoom> optionalExaminationRoom = this.examinationRoomRepository.findById(EXAM_ROOM_ID_EXISTS);

        assertTrue("Examination room is not present", optionalExaminationRoom.isPresent());
    }
}
