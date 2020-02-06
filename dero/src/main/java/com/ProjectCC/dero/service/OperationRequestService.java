package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.OperationRequestDTO;
import com.ProjectCC.dero.dto.OperationRoomRequestDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.repository.OperationRequestRepository;
import com.ProjectCC.dero.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

@Service
public class OperationRequestService {

    private OperationRequestRepository operationRequestRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OperationRequestService(UserRepository userRepository, OperationRequestRepository operationRequestRepository, ModelMapper modelMapper) {
        this.operationRequestRepository = operationRequestRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Void> save(OperationRequestDTO operationRequestDTO) {
        OperationRequest operationRequest = modelMapper.map(operationRequestDTO, OperationRequest.class);

        this.operationRequestRepository.save(operationRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<OperationRequestDTO> findOperations(String email,int page) {
        Pageable pageable = PageRequest.of(page, 10);
        ClinicAdministrator user = (ClinicAdministrator)userRepository.findByEmail(email);

        Page<OperationRequest> operations = operationRequestRepository.findByClinic(user.getClinic().getId(),pageable);
        List<OperationRequestDTO> operationRequestDTOS = new ArrayList<>();

        if(operations == null){
            return null;
        }else{
            for(OperationRequest o: operations){
                User patient = userRepository.getOne(o.getPatientId());
                User doctor = userRepository.getOne(o.getDoctorId());
                OperationRequestDTO or= OperationRequestDTO.builder()
                                        .date(o.getDate())
                                        .doctorId(o.getDoctorId())
                                        .id(o.getId())
                                        .duration(o.getDuration())
                                        .patientId(o.getPatientId())
                                        .patientName(patient.getFirstName()+" "+ patient.getLastName())
                                        .doctorName(doctor.getFirstName()+" "+doctor.getLastName())
                                        .pages(operations.getTotalPages())
                                        .build();

                operationRequestDTOS.add(or);
            }
            return operationRequestDTOS;
        }

    }

    public ResponseEntity<Void> reserveOperation(OperationRoomRequestDTO operationRoomRequest) {
        

    }
}
