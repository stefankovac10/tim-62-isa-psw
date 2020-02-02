package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ClinicAdministratorDTO;
import com.ProjectCC.dero.dto.ExaminationRequestDTO;
import com.ProjectCC.dero.dto.ExaminationRequestDetailsDTO;
import com.ProjectCC.dero.dto.OperationRequestDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.ClinicAdministrator;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.OperationRequest;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.service.ClinicAdministratorService;
import com.ProjectCC.dero.service.ClinicService;
import com.ProjectCC.dero.service.ExaminationRequestService;
import com.ProjectCC.dero.service.OperationRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public ResponseEntity<ClinicAdministratorDTO> save(@RequestBody ClinicAdministratorDTO clinicAdministratorDTO) {
        return new ResponseEntity<>(this.clinicAdministratorService.save(clinicAdministratorDTO), HttpStatus.OK);
    }

    @PostMapping(value = "scheduleNew/operation", consumes = "application/json")
    public ResponseEntity<Void> scheduleNewOperation(@RequestBody OperationRequestDTO operationRequestDTO) {
        return this.operationRequestService.save(operationRequestDTO);
    }

    @PostMapping(value = "scheduleNew/examination", consumes = "application/json")
    public ResponseEntity<Void> scheduleNewOperation(@RequestBody ExaminationRequestDTO examinationRequestDTO) {
        return this.examinationRequestService.save(examinationRequestDTO);
    }

    @GetMapping(value = "scheduledExaminations/{page}")
    public ResponseEntity<List<ExaminationRequestDetailsDTO>> getExaminations(@PathVariable int page) {
        return this.examinationRequestService.getAll(page);
    }

}
