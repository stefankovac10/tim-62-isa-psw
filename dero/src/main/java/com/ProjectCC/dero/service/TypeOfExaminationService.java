package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.TypeOfExamination;
import com.ProjectCC.dero.repository.TypeOfExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
