package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import com.ProjectCC.dero.dto.OperationRoomDTO;
import com.ProjectCC.dero.dto.RoomDTO;
import com.ProjectCC.dero.model.ExaminationRoom;
import com.ProjectCC.dero.model.OperationRoom;
import com.ProjectCC.dero.model.Room;
import com.ProjectCC.dero.service.ExaminationRoomService;
import com.ProjectCC.dero.service.OperationRoomService;
import com.ProjectCC.dero.service.RoomsService;
import lombok.val;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private RoomsService roomsService;

    @Autowired
    public RoomsController(OperationRoomService operationRoomService, ExaminationRoomService examinationRoomService,
                           RoomsService roomsService) {
        this.operationRoomService = operationRoomService;
        this.examinationRoomService = examinationRoomService;
        this.roomsService = roomsService;
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

    @GetMapping(value = "/search/{name}/{number}/{date}/{duration}/{page}")
    public ResponseEntity<List<RoomDTO>> searchRooms(@PathVariable String name, @PathVariable int number,
                                                     @PathVariable String date, @PathVariable Long duration,
                                                     @PathVariable int page) {
        DateTime dateTime = DateTime.parse(date);
        Duration d = new Duration(duration);
        return this.roomsService.search(name, number, dateTime, d, page);
    }

    @GetMapping(value = "/all/{page}")
    public ResponseEntity<List<RoomDTO>> allRooms(@PathVariable int page) {
        return new ResponseEntity<>(this.roomsService.getAll(page), HttpStatus.OK);
    }

    @GetMapping(value = "/examinationRequest/{id}/{page}")
    public ResponseEntity<List<ExaminationRoomDTO>> roomsForExamination(@PathVariable Long id, @PathVariable int page) {
        return this.roomsService.getRoomsForExamination(id, page);
    }

}
