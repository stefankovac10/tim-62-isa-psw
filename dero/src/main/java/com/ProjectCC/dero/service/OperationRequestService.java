package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.dto.OperationRequestDTO;
import com.ProjectCC.dero.dto.OperationRoomRequestDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.repository.OperationAppointmentRepository;
import com.ProjectCC.dero.repository.OperationRequestRepository;
import com.ProjectCC.dero.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

@Service
public class OperationRequestService {

    private OperationRequestRepository operationRequestRepository;
    private OperationAppointmentRepository operationAppointmentRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OperationRequestService(OperationAppointmentRepository operationAppointmentRepository,UserRepository userRepository, OperationRequestRepository operationRequestRepository, ModelMapper modelMapper) {
        this.operationRequestRepository = operationRequestRepository;
        this.operationAppointmentRepository = operationAppointmentRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Void> save(OperationRequestDTO operationRequestDTO) {
        OperationRequest operationRequest = modelMapper.map(operationRequestDTO, OperationRequest.class);

        this.operationRequestRepository.save(operationRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<OperationRequestDTO> findOperations(String email,int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC,"date"));
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
        Optional<OperationRequest> optionalOperationRequest = this.operationRequestRepository.findById(operationRoomRequest.getRequestId());
        if(!optionalOperationRequest.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        OperationRequest operationRequest = optionalOperationRequest.get();

        OperationAppointment oa = new OperationAppointment();
        System.out.println("idAPP:"+ oa.getId());
        oa.setDuration(operationRequest.getDuration());
        oa.setStartDate(operationRequest.getDate());
        oa.setEndDate(new DateTime(oa.getStartDate().getMillis() + oa.getDuration().getMillis(), DateTimeZone.UTC));
        OperationRoom or =  OperationRoom.builder()
                .id(operationRoomRequest.getRoom().getId())
                .build();
        System.out.println("idSob:"+ or.getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ClinicAdministrator ca = (ClinicAdministrator) userRepository.findByEmail(username);

        oa.setOperationRoom(or);
        oa.setClinic(ca.getClinic());

        Operation operation = new Operation();
        operation.setClinic(ca.getClinic());
        System.out.println("idKl:"+ ca.getClinic().getId());
        operation.setDate(oa.getStartDate());
        operation.setDuration(oa.getDuration());
        Patient patient = (Patient) userRepository.findById(operationRequest.getPatientId()).orElseGet(null);
        operation.setPatient(patient);
        System.out.println("idPATI:"+ patient.getId());
        operation.setMedicalRecord(patient.getMedicalRecord());
        operation.setOperationRoom(or);
        System.out.println("idoper:"+ operation.getId());
        Set<Doctor> doctors = new HashSet<>();
        for(DoctorDTO d: operationRoomRequest.getDoctors()){
            Doctor doctor = Doctor.builder()
                    .id(d.getId())
                    .build();
            System.out.println("idDOK"+ doctor.getId());
            doctors.add(doctor);
        }
        operation.setDoctors(doctors);
        oa.setOperation(operation);
        this.operationRequestRepository.delete(operationRequest);
        this.operationAppointmentRepository.save(oa);


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
