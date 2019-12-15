package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import com.ProjectCC.dero.dto.OperationRoomDTO;
import com.ProjectCC.dero.model.OperationRoom;
import com.ProjectCC.dero.service.ExaminationRoomService;
import com.ProjectCC.dero.service.OperationRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/api/rooms")
@CrossOrigin(origins = "http://localhost:8081")
public class RoomsController {
    private OperationRoomService operationRoomService;
    private ExaminationRoomService examinationRoomService;

    @Autowired
    public RoomsController(OperationRoomService operationRoomService, ExaminationRoomService examinationRoomService) {
        this.operationRoomService = operationRoomService;
        this.examinationRoomService = examinationRoomService;
    }

    @PostMapping(value = "/operation", consumes = "application/json")
    public ResponseEntity<OperationRoomDTO> addOperationRoom(@RequestBody OperationRoomDTO operationRoom) {
        return this.operationRoomService.save(operationRoom);
    }

    @PostMapping(value = "/examination", consumes = "application/json")
    public  ResponseEntity<ExaminationRoomDTO> addExaminationRoom(@RequestBody ExaminationRoomDTO examinationRoom) {
        return this.examinationRoomService.save(examinationRoom);
    }

    @GetMapping(value = "/operation/all")
    public ResponseEntity<List<OperationRoomDTO>> getAllOR() {
        return this.operationRoomService.getAll();
    }

    @GetMapping(value = "/examination/all")
    public ResponseEntity<List<ExaminationRoomDTO>> getAllER() {
        return this.examinationRoomService.getAll();
    }

    @DeleteMapping(value = "/examination/{id}")
    public ResponseEntity<Void> deleteER(@PathVariable Long id) {
        return this.examinationRoomService.delete(id);
    }

    @DeleteMapping(value = "/operation/{id}")
    public ResponseEntity<Void> deleteOR(@PathVariable Long id) {
        return this.operationRoomService.delete(id);
    }

    @PutMapping(value = "/operation")
    public ResponseEntity<Long> updateOR(@RequestBody OperationRoomDTO operationRoomDTO) {
        return this.operationRoomService.update(operationRoomDTO);
    }

    @PutMapping(value = "/examination")
    public ResponseEntity<Long> updateER(@RequestBody ExaminationRoomDTO examinationRoomDTO) {
        return this.examinationRoomService.update(examinationRoomDTO);
    }

    @GetMapping(value = "/operation/{id}")
    public ResponseEntity<OperationRoomDTO> getOrId(@PathVariable Long id) {
        return this.operationRoomService.findById(id);
    }

    @GetMapping(value = "/examination/{id}")
    public ResponseEntity<ExaminationRoomDTO> getErId(@PathVariable Long id) {
        return this.examinationRoomService.findById(id);
    }
}
