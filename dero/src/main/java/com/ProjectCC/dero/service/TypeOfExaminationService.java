package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.TypeOfExaminationDTO;
import com.ProjectCC.dero.model.TypeOfExamination;
import com.ProjectCC.dero.repository.TypeOfExaminationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeOfExaminationService {

    private TypeOfExaminationRepository typeOfExaminationRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TypeOfExaminationService(TypeOfExaminationRepository typeOfExaminationRepository,
                                    ModelMapper modelMapper) {
        this.typeOfExaminationRepository = typeOfExaminationRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<TypeOfExaminationDTO> update(TypeOfExaminationDTO typeDTO) {
        TypeOfExamination type = modelMapper.map(typeDTO, TypeOfExamination.class);
        this.typeOfExaminationRepository.save(type);
        return new ResponseEntity<>(typeDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<TypeOfExaminationDTO>> getAll() {
        List<TypeOfExamination> list = typeOfExaminationRepository.findAll();

        List<TypeOfExaminationDTO> listDTO = new ArrayList<>();
        for (TypeOfExamination t : list) {
            listDTO.add(TypeOfExaminationDTO.builder()
            .id(t.getId())
            .name(t.getName())
            .description(t.getDescription())
            .build());
        }

        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    public ResponseEntity<TypeOfExaminationDTO> add(TypeOfExaminationDTO typeDTO) {
        TypeOfExamination type = modelMapper.map(typeDTO, TypeOfExamination.class);
        type = this.typeOfExaminationRepository.save(type);
        typeDTO.setId(type.getId());
        return new ResponseEntity<>(typeDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<TypeOfExaminationDTO> delete(Long id) {
        this.typeOfExaminationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public TypeOfExamination findByName(String type) {
        return this.typeOfExaminationRepository.findByName(type);
    }
}
