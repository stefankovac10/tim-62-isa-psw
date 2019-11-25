package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.OperationRoomDTO;
import com.ProjectCC.dero.model.OperationRoom;
import com.ProjectCC.dero.service.OperationRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "/api/rooms")
public class OperationRoomController {
    private OperationRoomService operationRoomService;
//    private ExaminationRoomService examinationRoomService;

    @Autowired
    public OperationRoomController(OperationRoomService operationRoomService) {
        this.operationRoomService = operationRoomService;
    }

    @PostMapping(value = "/operation", consumes = "application/json")
    public ResponseEntity<OperationRoomDTO> addOperationRoom(@RequestBody  OperationRoomDTO operationRoom) {
        return this.operationRoomService.save(operationRoom);
    }
}
