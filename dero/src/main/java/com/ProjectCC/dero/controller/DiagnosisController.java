package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.model.Diagnosis;
import com.ProjectCC.dero.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/diagnosis")
public class DiagnosisController {

    private DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Diagnosis>> getAllClinics() {

        List<Diagnosis> diagnosis = diagnosisService.findAll();

        return new ResponseEntity<>(diagnosis, HttpStatus.OK);
    }

    @PostMapping( consumes = "application/json")
    public ResponseEntity<Diagnosis> save(@RequestBody Diagnosis diagnosisFE){
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setCode(diagnosisFE.getCode());
        diagnosis.setName(diagnosisFE.getName());
        diagnosis.setDescription(diagnosisFE.getDescription());

        diagnosis = diagnosisService.save(diagnosis);
        return new ResponseEntity<>(diagnosis, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        Diagnosis diagnosis  = diagnosisService.findOne(id);

        if (diagnosis != null) {
            diagnosisService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Diagnosis> getCourse(@PathVariable Long id) {

        Diagnosis diagnosis = diagnosisService.findOne(id);

        if (diagnosis == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(diagnosis, HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Diagnosis> update(@RequestBody Diagnosis diagnosis){
        diagnosisService.update(diagnosis);
        return new ResponseEntity<>(diagnosis, HttpStatus.OK);
    }

}
