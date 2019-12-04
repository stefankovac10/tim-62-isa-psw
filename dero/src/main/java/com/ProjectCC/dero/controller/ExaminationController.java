package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ExaminationDTO;
import com.ProjectCC.dero.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/api/examination")
public class ExaminationController {

    private ExaminationService examinationService;

    @Autowired
    public ExaminationController(ExaminationService examinationService) {
        this.examinationService = examinationService;
    }

    @PostMapping( consumes = "application/json")
    public ResponseEntity<Void> save(@RequestBody ExaminationDTO examinationDTO){
        examinationService.save(examinationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExaminationDTO> getMedicalRecord(@PathVariable Long id) {
        return new ResponseEntity<>(new ExaminationDTO(examinationService.getOne(id)), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ExaminationDTO> update(@RequestBody ExaminationDTO examinationDTO){
        return new ResponseEntity<>(examinationService.edit(examinationDTO), HttpStatus.OK);
    }

}
