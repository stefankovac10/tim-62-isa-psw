package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ExaminationDTO;
import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import com.ProjectCC.dero.service.ExaminationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value="/api/examination")
public class ExaminationController {

    private ExaminationService examinationService;
    private ModelMapper modelMapper;

    @Autowired
    public ExaminationController(ExaminationService examinationService, ModelMapper modelMapper) {
        this.examinationService = examinationService;
        this.modelMapper = modelMapper;
    }
    
    @PostMapping( consumes = "application/json")
    public ResponseEntity<Void> save(@RequestBody ExaminationDTO examinationDTO){
        examinationService.save(examinationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping( consumes = "application/json", value = "/addReport")
    public ResponseEntity<Void> addReport(@RequestBody ExaminationDTO examinationDTO){
        examinationService.addExaminationReport(examinationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExaminationDTO> getExamination(@PathVariable Long id) {
        return new ResponseEntity<>(examinationService.getOne(id), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ExaminationDTO> update(@RequestBody ExaminationDTO examinationDTO){
        return new ResponseEntity<>(examinationService.edit(examinationDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/addQuick", consumes = "application/json")
    public ResponseEntity<Void> addQuickExamination(@RequestBody ExaminationDTO examinationDTO) {
        this.examinationService.addNewQuick(examinationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
