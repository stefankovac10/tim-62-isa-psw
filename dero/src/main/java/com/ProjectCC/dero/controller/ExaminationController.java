package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.ExaminationDTO;
import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.service.ExaminationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/check/{id}")
    public ResponseEntity<Void> check(@PathVariable Long id) {
        if(examinationService.check(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/doc/{email:.+}/{role}")
    public ResponseEntity<List<ExaminationDTO>> getDocExamination(@PathVariable String email,@PathVariable String role) {
        return new ResponseEntity<>(examinationService.findDocExamination(email,role), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ExaminationDTO> update(@RequestBody ExaminationDTO examinationDTO){
        ExaminationDTO examination = examinationService.edit(examinationDTO);
        if(examination == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(examinationService.edit(examinationDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/addQuick", consumes = "application/json")
    public ResponseEntity<Void> addQuickExamination(@RequestBody ExaminationDTO examinationDTO) {
        this.examinationService.addNewQuick(examinationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/doctor/{doctorID}/{date}")
    public ResponseEntity<List<ExaminationDTO>> getExaminationsByDoctorsID(@PathVariable Long doctorID, @PathVariable String date) {
        return this.examinationService.getEximinationsByDoctorsID(doctorID, date);
    }

    @GetMapping(value = "/patient/{patientsEmail:.+}")
    public ResponseEntity<List<ExaminationDTO>> getExaminationsByPatientsEmail(@PathVariable String patientsEmail) {
        return this.examinationService.getExaminationByPatientsEmail(patientsEmail);
    }

}
