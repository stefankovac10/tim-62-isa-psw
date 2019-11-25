package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.OperationRoomDTO;
import com.ProjectCC.dero.model.OperationRoom;
import com.ProjectCC.dero.repository.OperationRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
