package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.service.ClinicAdministratorService;
import com.ProjectCC.dero.service.ExaminationRequestService;
import com.ProjectCC.dero.service.OperationRequestService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/cadmin")
public class ClinicAdministratorController {

    private ClinicAdministratorService clinicAdministratorService;
    private OperationRequestService operationRequestService;
    private ExaminationRequestService examinationRequestService;

    @Autowired
    public ClinicAdministratorController(ClinicAdministratorService clinicAdministratorService, OperationRequestService operationRequestService
                                        , ExaminationRequestService examinationRequestService) {
        this.clinicAdministratorService = clinicAdministratorService;
        this.operationRequestService = operationRequestService;
        this.examinationRequestService = examinationRequestService;
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Long> updateAdmin(@PathVariable ClinicAdministratorDTO cadminDTO) {
        return new ResponseEntity<>(this.clinicAdministratorService.update(cadminDTO), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_CCADMIN')")
    public ResponseEntity<ClinicAdministratorDTO> save(@RequestBody ClinicAdministratorDTO clinicAdministratorDTO) {
        ClinicAdministratorDTO cadmin = clinicAdministratorService.save(clinicAdministratorDTO);
        if(cadmin == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cadmin, HttpStatus.CREATED);
    }

    @PostMapping(value = "scheduleNew/operation", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public ResponseEntity<Void> scheduleNewOperation(@RequestBody OperationRequestDTO operationRequestDTO) {
        return this.operationRequestService.save(operationRequestDTO);
    }

    @PostMapping(value = "scheduleNew/examination", consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public ResponseEntity<Void> scheduleNewOperation(@RequestBody ExaminationRequestDTO examinationRequestDTO) {
        return this.examinationRequestService.save(examinationRequestDTO);
    }

    @GetMapping(value = "scheduledExaminations/{id}/{page}")
    @PreAuthorize("hasRole('ROLE_CADMIN') || hasRole('ROLE_DOCTOR')")
    public ResponseEntity<List<ExaminationRequestDetailsDTO>> getExaminations(@PathVariable Long id, @PathVariable int page) {
        return this.examinationRequestService.getAll(id, page);

    }

    @PutMapping(value = "reserve")
    public ResponseEntity<Void> reserveRoom(@RequestBody ExaminationRoomDTO examinationRoomDTO) {
        Long requestId = examinationRoomDTO.getRequestId();
        Long roomId = examinationRoomDTO.getId();
        DateTime nextAvailable = examinationRoomDTO.getNextAvailable();
        return this.examinationRequestService.reserve(requestId, roomId, nextAvailable);
    }

    @PostMapping(value = "reserveOperation")
    public ResponseEntity<Void> reserveRoomOperation(@RequestBody OperationRoomRequestDTO operationRoomRequest) {
        return this.operationRequestService.reserveOperation(operationRoomRequest);
    }

}
