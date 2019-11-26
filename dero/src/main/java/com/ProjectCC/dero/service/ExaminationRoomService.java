package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import com.ProjectCC.dero.model.ExaminationRoom;
import com.ProjectCC.dero.repository.ExaminationRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.EditorKit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminationRoomService {
    private ExaminationRoomRepository examinationRoomRepository;

    @Autowired
    public ExaminationRoomService(ExaminationRoomRepository examinationRoomRepository) {
        this.examinationRoomRepository = examinationRoomRepository;
    }

    public ResponseEntity<ExaminationRoomDTO> save(ExaminationRoomDTO examinationRoom) {
        ExaminationRoom er = new ExaminationRoom(examinationRoom);

        er = this.examinationRoomRepository.save(er);
        examinationRoom.setId(er.getId());

        return new ResponseEntity<>(examinationRoom, HttpStatus.CREATED);
    }

    public ResponseEntity<List<ExaminationRoomDTO>> getAll() {
        List<ExaminationRoom> list = this.examinationRoomRepository.findAll();
        List<ExaminationRoomDTO> ret = new ArrayList<>();

        for (ExaminationRoom or : list) {
            ret.add(new ExaminationRoomDTO(or.getId(), or.getNumber(), or.getName()));
        }

        return new ResponseEntity<>(ret, HttpStatus.FOUND);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.examinationRoomRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
