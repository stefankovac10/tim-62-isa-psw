package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.dto.ExaminationDTO;
import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.ExaminationAppointment;
import com.ProjectCC.dero.model.ExaminationRoom;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.ExaminationAppointmentRepository;
import com.ProjectCC.dero.repository.ExaminationRepository;
import com.ProjectCC.dero.repository.ExaminationRoomRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.EditorKit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationRoomService {
    private ExaminationRoomRepository examinationRoomRepository;
    private ExaminationRepository examinationRepository;
    private ExaminationAppointmentRepository examinationAppointmentRepository;
    private ClinicRepository clinicRepository;

    @Autowired
    public ExaminationRoomService(ExaminationRoomRepository examinationRoomRepository, ExaminationRepository examinationRepository,
                                  ExaminationAppointmentRepository examinationAppointmentRepository, ClinicRepository clinicRepository) {
        this.examinationRoomRepository = examinationRoomRepository;
        this.examinationRepository = examinationRepository;
        this.examinationAppointmentRepository = examinationAppointmentRepository;
        this.clinicRepository = clinicRepository;
    }

    public ResponseEntity<ExaminationRoomDTO> save(ExaminationRoomDTO examinationRoom) {
        Optional<Clinic> opt = this.clinicRepository.findById((long) 1);
        Clinic c = opt.get();
        ClinicDTO clinic = ClinicDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .address(c.getAddress())
                .build();
        ExaminationRoom er = ExaminationRoom.builder()
                .id(examinationRoom.getId())
                .number(examinationRoom.getNumber())
                .name(examinationRoom.getName())
                .clinic(c)
                .build();

        er = this.examinationRoomRepository.save(er);
        examinationRoom.setId(er.getId());
        examinationRoom.setClinic(clinic);

        return new ResponseEntity<>(examinationRoom, HttpStatus.OK);
    }

    public ResponseEntity<List<ExaminationRoomDTO>> getAll() {
        List<ExaminationRoom> list = this.examinationRoomRepository.findAll();
        List<ExaminationRoomDTO> ret = new ArrayList<>();

        for (ExaminationRoom er : list) {
            ret.add(ExaminationRoomDTO.builder()
                    .id(er.getId())
                    .number(er.getNumber())
                    .name(er.getName())
                    .build());
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(Long id) {
        Optional<ExaminationRoom> optionalExaminationRoom = this.examinationRoomRepository.findById(id);
        if (!optionalExaminationRoom.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ExaminationRoom examinationRoom = optionalExaminationRoom.get();
        List<Examination> examinations = this.examinationRepository.findByExaminationRoom(examinationRoom);
        List<ExaminationAppointment> examinationAppointments = this.examinationAppointmentRepository.findByExaminationRoom(examinationRoom);
        DateTime now = new DateTime();

        for (ExaminationAppointment examinationAppointment: examinationAppointments) {
            if (examinationAppointment.getStartDate().isAfter(now))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            examinationAppointment.setExaminationRoom(null);
            this.examinationAppointmentRepository.save(examinationAppointment);
        }

        for (Examination examination : examinations) {
            examination.setExaminationRoom(null);
            this.examinationRepository.save(examination);
        }

        this.examinationRoomRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Long> update(ExaminationRoomDTO examinationRoomDTO) {
        Optional<ExaminationRoom> optional = this.examinationRoomRepository.findById(examinationRoomDTO.getId());
        if (!optional.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ExaminationRoom er = optional.get();
        er.setName(examinationRoomDTO.getName());
        er.setNumber(examinationRoomDTO.getNumber());
        // ako treba preglede?
        this.examinationRoomRepository.save(er);
        return new ResponseEntity<>(er.getId(), HttpStatus.OK);
    }

    public ResponseEntity<ExaminationRoomDTO> findById(Long id) {
        Optional<ExaminationRoom> er = this.examinationRoomRepository.findById(id);
        if (!er.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ExaminationRoom examinationRoom = er.get();
        ExaminationRoomDTO ret = ExaminationRoomDTO.builder()
                .id(examinationRoom.getId())
                .name(examinationRoom.getName())
                .number(examinationRoom.getNumber())
                .build();
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    public ResponseEntity<List<ExaminationRoomDTO>> getByClinicId(Long id) {
        Optional<Clinic> optionalClinic = this.clinicRepository.findById(id);
        if (!optionalClinic.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Clinic clinic = optionalClinic.get();

        List<ExaminationRoom> list = this.examinationRoomRepository.findByClinic(clinic);
        List<ExaminationRoomDTO> ret = new ArrayList<>();

        for (ExaminationRoom er : list) {
            ret.add(ExaminationRoomDTO.builder()
                    .id(er.getId())
                    .number(er.getNumber())
                    .name(er.getName())
                    .build());
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
