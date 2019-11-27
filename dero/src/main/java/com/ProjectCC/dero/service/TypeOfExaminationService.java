package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.TypeOfExaminationDTO;
import com.ProjectCC.dero.model.TypeOfExamination;
import com.ProjectCC.dero.repository.TypeOfExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeOfExaminationService {

    private TypeOfExaminationRepository typeOfExaminationRepository;

    @Autowired
    public TypeOfExaminationService(TypeOfExaminationRepository typeOfExaminationRepository) {
        this.typeOfExaminationRepository = typeOfExaminationRepository;
    }

    public TypeOfExamination save(TypeOfExamination type) {
        return typeOfExaminationRepository.save(type);
    }

    public List<TypeOfExamination> findAll() {
        return typeOfExaminationRepository.findAll();
    }

    public void update(TypeOfExaminationDTO typeDTO) {
        typeOfExaminationRepository.update(typeDTO.getName(), typeDTO.getDescription(), typeDTO.getId());
    }

    public ResponseEntity<List<TypeOfExaminationDTO>> getAll() {
        List<TypeOfExamination> list = typeOfExaminationRepository.findAll();

        List<TypeOfExaminationDTO> listDTO = new ArrayList<>();
        for (TypeOfExamination t : list) {
            TypeOfExaminationDTO tdo = new TypeOfExaminationDTO(t);
            listDTO.add(tdo);
        }

        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }
}
