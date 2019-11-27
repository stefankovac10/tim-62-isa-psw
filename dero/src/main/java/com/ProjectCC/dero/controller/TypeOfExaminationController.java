package com.ProjectCC.dero.controller;

import com.ProjectCC.dero.dto.TypeOfExaminationDTO;
import com.ProjectCC.dero.model.TypeOfExamination;
import com.ProjectCC.dero.service.TypeOfExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.*;

@RestController
@RequestMapping(value = "/api/types")
@CrossOrigin(origins = "http://localhost:8081")
public class TypeOfExaminationController {
    private TypeOfExaminationService typeOfExaminationService;

    @Autowired
    public TypeOfExaminationController(TypeOfExaminationService typeOfExaminationService) {
        this.typeOfExaminationService = typeOfExaminationService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<TypeOfExaminationDTO> addType(@RequestBody TypeOfExaminationDTO typeDTO) {

        TypeOfExamination type = new TypeOfExamination();
        type.setName(typeDTO.getName());
        type.setDescription(typeDTO.getDescription());

        type = typeOfExaminationService.save(type);

        return new ResponseEntity<>(new TypeOfExaminationDTO(type), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<TypeOfExaminationDTO>> getAll() {
        return this.typeOfExaminationService.getAll();
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<TypeOfExaminationDTO> update(@RequestBody TypeOfExaminationDTO typeDTO) {
        typeOfExaminationService.update(typeDTO);

        return new ResponseEntity<>(typeDTO, HttpStatus.OK);
    }

}
