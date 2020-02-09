package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ExaminationDTO;
import com.ProjectCC.dero.dto.OperationDTO;
import com.ProjectCC.dero.service.OperationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/api/operation")
public class OperationController {

    private OperationService operationService;
    private ModelMapper modelMapper;

    @Autowired
    public OperationController(OperationService operationService, ModelMapper modelMapper) {
        this.operationService = operationService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<OperationDTO> getOperation(@PathVariable Long id) {
        return new ResponseEntity<>(operationService.getOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/doc/{email:.+}/{role}")
    public ResponseEntity<List<OperationDTO>> getDocOperation(@PathVariable String email,@PathVariable String role) {
        return new ResponseEntity<>(operationService.findDocOperation(email, role), HttpStatus.OK);
    }

    @GetMapping(value = "/patient/{patientsEmail:.+}")
    public ResponseEntity<List<OperationDTO>> getOperationsByPatientsEmail(@PathVariable String patientsEmail) {
        return this.operationService.getOperationsByPatientsEmail(patientsEmail);
    }

}
