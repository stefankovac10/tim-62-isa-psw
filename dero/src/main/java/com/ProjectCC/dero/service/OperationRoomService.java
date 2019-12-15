package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.dto.OperationRoomDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Operation;
import com.ProjectCC.dero.model.OperationRoom;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.OperationRoomRepository;
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
    private ClinicRepository clinicRepository;

    @Autowired
    public OperationRoomService(OperationRoomRepository operationRoomRepository, ClinicRepository clinicRepository) {
        this.operationRoomRepository = operationRoomRepository;
        this.clinicRepository = clinicRepository;
    }

    public ResponseEntity<OperationRoomDTO> save(OperationRoomDTO operationRoom) {
        Optional<Clinic> opt = this.clinicRepository.findById((long) 1);
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

        return new ResponseEntity<>(ret, HttpStatus.FOUND);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.operationRoomRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Long> update(OperationRoomDTO operationRoomDTO) {
        Optional<OperationRoom> optional = this.operationRoomRepository.findById(operationRoomDTO.getId());
        OperationRoom or = optional.get();
        or.setName(operationRoomDTO.getName());
        or.setNumber(operationRoomDTO.getNumber());
        // ako treba operacije?
        this.operationRoomRepository.save(or);
        return new ResponseEntity<>(or.getId(), HttpStatus.OK);
    }

    public ResponseEntity<OperationRoomDTO> findById(Long id) {
        Optional<OperationRoom> or = this.operationRoomRepository.findById(id);
        OperationRoom operationRoom = or.get();
        OperationRoomDTO ret = OperationRoomDTO.builder()
                .id(operationRoom.getId())
                .name(operationRoom.getName())
                .number(operationRoom.getNumber())
                .build();
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
