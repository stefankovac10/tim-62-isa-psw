package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.VacationRequestDTO;
import com.ProjectCC.dero.model.VacationRequest;
import com.ProjectCC.dero.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(path = "/api/vacs")
public class VacationRequestController {
    private VacationRequestService vacationRequestService;

    @Autowired
    public VacationRequestController(VacationRequestService vacationRequestService) {
        this.vacationRequestService = vacationRequestService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<VacationRequestDTO> addNew(@RequestBody VacationRequestDTO vacationRequestDTO) {
        return new ResponseEntity<>(this.vacationRequestService.add(vacationRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<VacationRequestDTO>> getAll() {
        return new ResponseEntity<>(this.vacationRequestService.getAll(), HttpStatus.OK);
    }


}
