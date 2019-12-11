package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.DiagnosisDTO;
import com.ProjectCC.dero.model.Diagnosis;
import com.ProjectCC.dero.service.DiagnosisService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/api/diagnosis")
public class DiagnosisController {

    private DiagnosisService diagnosisService;
    private ModelMapper modelMapper;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService, ModelMapper modelMapper) {
        this.diagnosisService = diagnosisService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<DiagnosisDTO>> getAllDiagnosis() {

        List<Diagnosis> diagnosis = diagnosisService.findAll();

        List<DiagnosisDTO> diagnosisDTOS = new ArrayList<>();
        for (Diagnosis d : diagnosis) {
            diagnosisDTOS.add(new DiagnosisDTO(d));
        }

        return new ResponseEntity<>(diagnosisDTOS, HttpStatus.OK);

    }

    @PostMapping( consumes = "application/json")
    public ResponseEntity<DiagnosisDTO> save(@RequestBody DiagnosisDTO diagnosisDTO){
        Diagnosis diagnosis = modelMapper.map(diagnosisDTO, Diagnosis.class);

        diagnosis = diagnosisService.save(diagnosis);
        if(diagnosis == null){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(new DiagnosisDTO(diagnosis), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDiagnosis(@PathVariable Long id) {

        Diagnosis diagnosis  = diagnosisService.findOne(id);

        if (diagnosis != null) {
            diagnosisService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DiagnosisDTO> getDiagnosis(@PathVariable Long id) {

        Diagnosis diagnosis = diagnosisService.findOne(id);

        if (diagnosis == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DiagnosisDTO(diagnosis), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<DiagnosisDTO> update(@RequestBody DiagnosisDTO diagnosisDTO){
        Diagnosis diagnosis = modelMapper.map(diagnosisDTO, Diagnosis.class);
        diagnosisService.update(diagnosis);
        return new ResponseEntity<>(diagnosisDTO, HttpStatus.OK);
    }

}
