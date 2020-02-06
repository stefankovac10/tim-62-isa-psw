package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.dto.OperationRoomDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.OperationAppointmentRepository;
import com.ProjectCC.dero.repository.OperationRepository;
import com.ProjectCC.dero.repository.OperationRoomRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationRoomService {
    private OperationRoomRepository operationRoomRepository;
    private OperationRepository operationRepository;
    private OperationAppointmentRepository operationAppointmentRepository;
    private ClinicRepository clinicRepository;

    @Autowired
    public OperationRoomService(OperationRoomRepository operationRoomRepository, OperationRepository operationRepository,
                                OperationAppointmentRepository operationAppointmentRepository, ClinicRepository clinicRepository) {
        this.operationRoomRepository = operationRoomRepository;
        this.operationRepository = operationRepository;
        this.operationAppointmentRepository = operationAppointmentRepository;
        this.clinicRepository = clinicRepository;
    }

    public ResponseEntity<OperationRoomDTO> save(OperationRoomDTO operationRoom) {
        Optional<Clinic> opt = this.clinicRepository.findById(operationRoom.getClinic().getId());
        if (!opt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Clinic c = opt.get();

        ClinicDTO clinic = ClinicDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .address(c.getAddress())
                .build();
        OperationRoom or = OperationRoom.builder()
                .id(operationRoom.getId())
                .name(operationRoom.getName())
                .number(operationRoom.getNumber())
                .clinic(c)
                .build();

        or = operationRoomRepository.save(or);
        operationRoom.setId(or.getId());
        operationRoom.setClinic(clinic);

        return new ResponseEntity<>(operationRoom, HttpStatus.CREATED);
    }

    public ResponseEntity<List<OperationRoomDTO>> getAll() {
        List<OperationRoom> list = this.operationRoomRepository.findAll();
        List<OperationRoomDTO> ret = new ArrayList<>();

        for (OperationRoom or : list) {
            ret.add(OperationRoomDTO.builder()
                    .id(or.getId())
                    .name(or.getName())
                    .number(or.getNumber())
                    .build());
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long id) {
        Optional<OperationRoom> optionalOperationRoom = this.operationRoomRepository.findById(id);
        if (!optionalOperationRoom.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        OperationRoom operationRoom = optionalOperationRoom.get();
        List<Operation> operations = this.operationRepository.findByOperationRoom(operationRoom);
        List<OperationAppointment> operationAppointments = this.operationAppointmentRepository.findByOperationRoom(operationRoom);
        DateTime now = new DateTime();

        for (OperationAppointment operationAppointment: operationAppointments) {
            if (operationAppointment.getStartDate().isAfter(now))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            operationAppointment.setOperationRoom(null);
            this.operationAppointmentRepository.save(operationAppointment);
        }

        for (Operation operation : operations) {
            operation.setOperationRoom(null);
            this.operationRepository.save(operation);
        }

        this.operationRoomRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Long> update(OperationRoomDTO operationRoomDTO) {
        Optional<OperationRoom> optional = this.operationRoomRepository.findById(operationRoomDTO.getId());
        if (!optional.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        OperationRoom or = optional.get();
        or.setName(operationRoomDTO.getName());
        or.setNumber(operationRoomDTO.getNumber());
        // ako treba operacije?
        this.operationRoomRepository.save(or);
        return new ResponseEntity<>(or.getId(), HttpStatus.OK);
    }

    public ResponseEntity<OperationRoomDTO> findById(Long id) {
        Optional<OperationRoom> or = this.operationRoomRepository.findById(id);
        if (!or.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        OperationRoom operationRoom = or.get();
        OperationRoomDTO ret = OperationRoomDTO.builder()
                .id(operationRoom.getId())
                .name(operationRoom.getName())
                .number(operationRoom.getNumber())
                .build();
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
