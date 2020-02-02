package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.OperationDTO;
import com.ProjectCC.dero.dto.OperationRoomDTO;
import com.ProjectCC.dero.dto.PatientDTO;
import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.Operation;
import com.ProjectCC.dero.model.OperationRoom;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.repository.NurseRepository;
import com.ProjectCC.dero.repository.OperationRepository;
import com.ProjectCC.dero.repository.UserRepository;
import org.joda.time.Duration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationService {

    private OperationRepository operationRepository;
    private ModelMapper modelMapper;
    private DoctorRepository doctorRepository;
    private NurseRepository nurseRepository;
    private UserRepository userRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository, ModelMapper modelMapper, DoctorRepository doctorRepository, NurseRepository nurseRepository, UserRepository userRepository) {
        this.operationRepository = operationRepository;
        this.modelMapper = modelMapper;
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
        this.userRepository = userRepository;
    }


    public OperationDTO getOne(Long id) {
        Operation o = operationRepository.getOne(id);
        PatientDTO patientDTO = PatientDTO.builder()
                                .id(o.getPatient().getId())
                                .firstName(o.getPatient().getFirstName())
                                .lastName(o.getPatient().getLastName())
                                .build();
        OperationRoomDTO room = OperationRoomDTO.builder()
                                .id(o.getOperationRoom().getId())
                                .name(o.getOperationRoom().getName())
                                .number(o.getOperationRoom().getNumber())
                                .build();
        OperationDTO operationDTO = OperationDTO.builder()
                                    .duration(o.getDuration())
                                    .id(o.getId())
                                    .patient(patientDTO)
                                    .date(o.getDate())
                                    .operationRoom(room).build();
        return operationDTO;
    }

    public List<OperationDTO> findDocOperation(String email, String role) {
        User doctor  = userRepository.findByEmail(email);
        List<OperationDTO> operationDTOS = new ArrayList<>();
        List<Operation> operations =  new ArrayList<>();
        if(role.equals("ROLE_DOCTOR")){
            operations = doctorRepository.findDocOperation(doctor.getId());
        }else if(role.equals("ROLE_NURSE")){
            return operationDTOS;
        }

        for(Operation o: operations){
            PatientDTO patientDTO = PatientDTO.builder()
                    .id(o.getPatient().getId())
                    .firstName(o.getPatient().getFirstName())
                    .lastName(o.getPatient().getLastName())
                    .build();
            OperationRoomDTO room = OperationRoomDTO.builder()
                    .id(o.getOperationRoom().getId())
                    .name(o.getOperationRoom().getName())
                    .number(o.getOperationRoom().getNumber())
                    .build();
            OperationDTO operationDTO = OperationDTO.builder()
                    .duration(o.getDuration())
                    .id(o.getId())
                    .patient(patientDTO)
                    .date(o.getDate())
                    .operationRoom(room).build();
            operationDTOS.add(operationDTO);
        }

        return operationDTOS;
    }
}
