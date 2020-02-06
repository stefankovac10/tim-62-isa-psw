package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ExaminationRoomDTO;
import com.ProjectCC.dero.dto.RoomDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomsService {

    private RoomsRepository roomsRepository;
    private ExaminationAppointmentRepository examinationAppointmentRepository;
    private OperationAppointmentRepository operationAppointmentRepository;
    private ExaminationRoomRepository examinationRoomRepository;
    private OperationRoomRepository operationRoomRepository;
    private ExaminationRequestRepository examinationRequestRepository;
    private ClinicRepository clinicRepository;

    @Autowired
    public RoomsService(RoomsRepository roomsRepository, ExaminationAppointmentRepository examinationAppointmentRepository, OperationAppointmentRepository operationAppointmentRepository,
                        ExaminationRoomRepository examinationRoomRepository, OperationRoomRepository operationRoomRepository, ExaminationRequestRepository examinationRequestRepository,
                        ClinicRepository clinicRepository) {
        this.roomsRepository = roomsRepository;
        this.examinationAppointmentRepository = examinationAppointmentRepository;
        this.operationAppointmentRepository = operationAppointmentRepository;
        this.examinationRoomRepository = examinationRoomRepository;
        this.operationRoomRepository = operationRoomRepository;
        this.examinationRequestRepository = examinationRequestRepository;
        this.clinicRepository = clinicRepository;
    }

    public ResponseEntity<List<RoomDTO>> search(String name, int number, DateTime date, Duration duration, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        String sName = "%" + name + "%";
        Page<ExaminationRoom> eRooms = null;
        Page<OperationRoom> oRooms = null;
        if (number > -1) {
            eRooms = this.examinationRoomRepository.search(sName, number, pageable);
            oRooms = this.operationRoomRepository.search(sName, number, pageable);
        }
        else {
            eRooms = this.examinationRoomRepository.searchName(sName, pageable);
            oRooms = this.operationRoomRepository.searchName(sName, pageable);
        }

        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (ExaminationRoom r : eRooms.getContent()) {
            roomDTOS.add(RoomDTO.builder()
                .name(r.getName())
                .number(r.getNumber())
                .nextAvailable(findFirstAvailableForExamination(r, date, duration))
                .type("examination")
                .id(r.getId()).build());
        }
        for (OperationRoom r : oRooms.getContent()) {
            roomDTOS.add(RoomDTO.builder()
                    .name(r.getName())
                    .number(r.getNumber())
                    .nextAvailable(findFirstAvailableForOperation(r, date, duration))
                    .type("operation")
                    .id(r.getId()).build());
        }
        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }

    private DateTime findFirstAvailableForOperation(OperationRoom room, DateTime date, Duration duration) {
        DateTime now = new DateTime(DateTimeZone.UTC);
        List<OperationAppointment> scheduled = this.operationAppointmentRepository.findByRoomAndDate(room, now);
        // isto za operacioneappointmente
        if (scheduled.size() == 0) {
            return date;
        }

        scheduled.sort((oa1, oa2) -> {
            if (oa1.getStartDate() == null || oa2.getStartDate() == null)
                return 0;
            return oa1.getStartDate().compareTo(oa2.getStartDate());
        });

        DateTime dateEnd = new DateTime(date.getMillis() + duration.getMillis(), DateTimeZone.UTC);
        for (OperationAppointment oa : scheduled) {
            oa.setEndDate(new DateTime(oa.getStartDate().getMillis() + oa.getDuration().getMillis(), DateTimeZone.UTC));

            if (!date.isAfter(oa.getEndDate()) && !dateEnd.isBefore(oa.getStartDate())) {
                return findNextForOperation(scheduled, date, duration, dateEnd);
            }
        }
        return date;
    }

    private DateTime findNextForOperation(List<OperationAppointment> scheduled, DateTime date, Duration duration, DateTime dateEnd) {
        DateTime next = date;
        DateTime nextEnd = dateEnd;
        for (OperationAppointment oa : scheduled) {
            if (oa.getEndDate() == null) {
                oa.setEndDate(new DateTime(oa.getStartDate().getMillis() + oa.getDuration().getMillis(), DateTimeZone.UTC));
            }
            if (oa.getEndDate().isBefore(next)) continue;
            if (oa.getEndDate().isBefore(nextEnd)) {
                next = oa.getEndDate().plusMinutes(2); // prazan hod sobe
                nextEnd = next.plus(duration);
            }
        }
        return next;
    }

    public DateTime findFirstAvailableForExamination(ExaminationRoom room, DateTime date, Duration duration) {
        DateTime now = new DateTime();
        List<ExaminationAppointment> scheduled = this.examinationAppointmentRepository.findByRoomAndDate(room, now);
        // isto za operacioneappointmente
        if (scheduled.size() == 0) {
            return date;
        }

        scheduled.sort((ea1, ea2) -> {
            if (ea1.getStartDate() == null || ea2.getStartDate() == null)
                return 0;
            return ea1.getStartDate().compareTo(ea2.getStartDate());
        });

        DateTime dateEnd = new DateTime(date.getMillis() + duration.getMillis(), DateTimeZone.UTC);

        for (ExaminationAppointment ea : scheduled) {
            ea.setEndDate(new DateTime(ea.getStartDate().getMillis() + ea.getDuration().getMillis(), DateTimeZone.UTC));
            // da li ovaj sam isti koji je jebe li me, nadje li bas njega?
            if (!date.isAfter(ea.getEndDate()) && !dateEnd.isBefore(ea.getStartDate())) {
                return findNext(scheduled, date, duration, dateEnd);
            }
        }
        return date;
    }

    private DateTime findNext(List<ExaminationAppointment> scheduled, DateTime date, Duration duration, DateTime dateEnd) {
        DateTime next = date;
        DateTime nextEnd = dateEnd;
        for (ExaminationAppointment ea : scheduled) {
            if (ea.getEndDate() == null) {
                ea.setEndDate(new DateTime(ea.getStartDate().getMillis() + ea.getDuration().getMillis(), DateTimeZone.UTC));
            }
            if (ea.getEndDate().isBefore(next)) continue;
            if (ea.getEndDate().isBefore(nextEnd)) {
                next = ea.getEndDate().plusMinutes(2); // prazan hod sobe
                nextEnd = next.plus(duration);
            }
        }
        return next;
    }

    public ResponseEntity<List<RoomDTO>> getAll(Long id, int page) {
        Optional<Clinic> optionalClinic = this.clinicRepository.findById(id);
        if (!optionalClinic.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Clinic clinic = optionalClinic.get();
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = this.roomsRepository.findByClinic(clinic, pageable);

        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : roomPage.getContent()) {
            String type;
            if (r instanceof ExaminationRoom) {
                type = "examination";
            } else type = "operation";
            roomDTOS.add(RoomDTO.builder()
                    .name(r.getName())
                    .number(r.getNumber())
                    .type(type)
                    .id(r.getId()).build());
        }
        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }

    public ResponseEntity<List<ExaminationRoomDTO>> getRoomsForExamination(Long id, int page) {
        Optional<ExaminationRequest> optionalExaminationRequest = this.examinationRequestRepository.findById(id);
        if (!optionalExaminationRequest.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ExaminationRequest examinationRequest = optionalExaminationRequest.get();

        Pageable pageable = PageRequest.of(page, 10);
        Page<ExaminationRoom> examinationRooms = this.examinationRoomRepository.findAll(pageable);

        List<ExaminationRoomDTO> examinationRoomDTOS = new ArrayList<>();
        for (ExaminationRoom r : examinationRooms.getContent()) {
            examinationRoomDTOS.add(ExaminationRoomDTO.builder()
                    .name(r.getName())
                    .number(r.getNumber())
                    .nextAvailable(findFirstAvailableForExamination(r, examinationRequest.getExaminationAppointment().getStartDate(), examinationRequest.getExaminationAppointment().getDuration()))
                    .type("examination")
                    .id(r.getId()).build());
        }
        return new ResponseEntity<>(examinationRoomDTOS, HttpStatus.OK);
    }
}
