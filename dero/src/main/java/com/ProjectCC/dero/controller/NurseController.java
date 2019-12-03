package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.NurseDTO;
import com.ProjectCC.dero.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/nurse")
public class NurseController {
    private NurseService nurseService;

    @Autowired
    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<NurseDTO> addNurse(@RequestBody NurseDTO nurseDTO) {
        return new ResponseEntity<>(this.nurseService.add(nurseDTO), HttpStatus.CREATED);
    }
}
