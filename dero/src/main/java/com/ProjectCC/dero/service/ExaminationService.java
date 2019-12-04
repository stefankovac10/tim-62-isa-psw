package com.ProjectCC.dero.service;

import com.ProjectCC.dero.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationService {

    private ExaminationRepository examinationRepository;

    @Autowired
    public ExaminationService(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

}
