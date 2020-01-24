package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ExaminationRequestDTO;
import com.ProjectCC.dero.model.ExaminationRequest;
import com.ProjectCC.dero.repository.ExaminationRepository;
import com.ProjectCC.dero.repository.ExaminationRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExaminationRequestService {

    private ExaminationRequestRepository examinationRequestRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ExaminationRequestService(ExaminationRequestRepository examinationRequestRepository, ModelMapper modelMapper) {
        this.examinationRequestRepository = examinationRequestRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<Void> save(ExaminationRequestDTO examinationRequestDTO) {
        ExaminationRequest examinationRequest = this.modelMapper.map(examinationRequestDTO, ExaminationRequest.class);

        this.examinationRequestRepository.save(examinationRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
