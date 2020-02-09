package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.NurseDTO;
import com.ProjectCC.dero.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:8081")
@RequestMapping(path = "/api/nurse")
public class NurseController {
    private NurseService nurseService;

    @Autowired
    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize(" hasRole('ROLE_CADMIN')")
    public ResponseEntity<NurseDTO> addNurse(@RequestBody NurseDTO nurseDTO) {
        return new ResponseEntity<>(this.nurseService.add(nurseDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize(" hasRole('ROLE_CADMIN')")
    public ResponseEntity<Void> deleteNurse(@PathVariable Long id) {
        return this.nurseService.deleteNurse(id);
    }
}
