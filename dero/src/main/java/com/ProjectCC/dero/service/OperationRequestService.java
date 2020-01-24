package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.OperationRequestDTO;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.model.OperationRequest;
import com.ProjectCC.dero.model.Patient;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.repository.OperationRequestRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OperationRequestService {

    private OperationRequestRepository operationRequestRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OperationRequestService(OperationRequestRepository operationRequestRepository, ModelMapper modelMapper) {
        this.operationRequestRepository = operationRequestRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<Void> save(OperationRequestDTO operationRequestDTO) {
        OperationRequest operationRequest = modelMapper.map(operationRequestDTO, OperationRequest.class);

        this.operationRequestRepository.save(operationRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
