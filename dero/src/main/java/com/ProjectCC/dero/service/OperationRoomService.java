package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.OperationRoomDTO;
import com.ProjectCC.dero.model.OperationRoom;
import com.ProjectCC.dero.repository.OperationRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationRoomService {
    private OperationRoomRepository operationRoomRepository;

    @Autowired
    public OperationRoomService(OperationRoomRepository operationRoomRepository) {
        this.operationRoomRepository = operationRoomRepository;
    }

    public ResponseEntity<OperationRoomDTO> save(OperationRoomDTO operationRoom) {
        OperationRoom or = new OperationRoom(operationRoom);

        or = operationRoomRepository.save(or);
        operationRoom.setId(or.getId());

        return new ResponseEntity<>(operationRoom, HttpStatus.CREATED);
    }

    public ResponseEntity<List<OperationRoomDTO>> getAll() {
        List<OperationRoom> list = this.operationRoomRepository.findAll();
        List<OperationRoomDTO> ret = new ArrayList<>();

        for (OperationRoom or : list) {
            ret.add(new OperationRoomDTO(or.getId(), or.getNumber(), or.getName()));
        }

        return new ResponseEntity<>(ret, HttpStatus.FOUND);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.operationRoomRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Long> update(OperationRoomDTO operationRoomDTO) {
        Optional<OperationRoom> optional = this.operationRoomRepository.findById(operationRoomDTO.getId());
        OperationRoom or = optional.get();
        or.setName(operationRoomDTO.getName());
        or.setNumber(operationRoomDTO.getNumber());
        // ako treba operacije?
        this.operationRoomRepository.save(or);
        return new ResponseEntity<>(or.getId(), HttpStatus.OK);
    }
}
