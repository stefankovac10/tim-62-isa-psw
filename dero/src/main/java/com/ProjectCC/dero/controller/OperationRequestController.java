package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.OperationRequestDTO;
import com.ProjectCC.dero.service.OperationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/api/operationRequest")
public class OperationRequestController {

    private OperationRequestService operationRequestService;

    @Autowired
    public OperationRequestController(OperationRequestService operationRequestService) {
        this.operationRequestService = operationRequestService;
    }

    @GetMapping(value = "/{email:.+}/{page}")
    public ResponseEntity<List<OperationRequestDTO>> getOperations(@PathVariable String email,@PathVariable int page) {
        List<OperationRequestDTO> operationDTOS = operationRequestService.findOperations(email, page);

        if(operationDTOS == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(operationDTOS, HttpStatus.OK);
        }
    }
}
