package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.*;

import com.ProjectCC.dero.exception.BadExaminationRequest;
import com.ProjectCC.dero.exception.UserNotFoundException;
import com.ProjectCC.dero.exceptions.*;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EnableScheduling
public class ExaminationRequestService {

    private ExaminationRequestRepository examinationRequestRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ExaminationRoomRepository examinationRoomRepository;
    private ExaminationRepository examinationRepository;
    private ClinicRepository clinicRepository;
    private TypeOfExaminationRepository typeOfExaminationRepository;
    private ModelMapper modelMapper;
    private ExaminationAppointmentRepository examinationAppointmentRepository;
    private TypeOfExaminationRepository typeOfExaminationRepository;
    private RoomsService roomsService;
    private VacationRequestRepository vacationRequestRepository;

    @Autowired
    public ExaminationRequestService(ExaminationRequestRepository examinationRequestRepository, DoctorRepository doctorRepository,
                                     PatientRepository patientRepository, ExaminationRoomRepository examinationRoomRepository,
                                     ExaminationRepository examinationRepository, ClinicRepository clinicRepository, ModelMapper modelMapper,
                                     ExaminationAppointmentRepository examinationAppointmentRepository, RoomsService roomsService,
                                     TypeOfExaminationRepository typeOfExaminationRepository, VacationRequestRepository vacationRequestRepository) {
        this.examinationRequestRepository = examinationRequestRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.examinationRoomRepository = examinationRoomRepository;
        this.examinationRepository = examinationRepository;
        this.clinicRepository = clinicRepository;
        this.typeOfExaminationRepository = typeOfExaminationRepository;
        this.modelMapper = modelMapper;
        this.examinationAppointmentRepository = examinationAppointmentRepository;
        this.roomsService = roomsService;
        this.typeOfExaminationRepository = typeOfExaminationRepository;
        this.vacationRequestRepository = vacationRequestRepository;
    }

    public ResponseEntity<Void> save(ExaminationRequestDTO examinationRequestDTO) {
        ExaminationRequest examinationRequest = this.modelMapper.map(examinationRequestDTO, ExaminationRequest.class);
        ExaminationAppointment examinationAppointment = new ExaminationAppointment();
        examinationAppointment.setStartDate(examinationRequestDTO.getDate());
        examinationAppointment.setDuration(new Duration(examinationRequestDTO.getDuration()));
        examinationAppointment.setExaminationRequest(examinationRequest);
        Doctor doc = this.doctorRepository.findById(examinationRequestDTO.getDoctorId()).orElseGet(null);
        if (doc != null) {
            examinationAppointment.setClinic(doc.getClinic());
            examinationRequest.setExaminationAppointment(examinationAppointment);
            this.examinationRequestRepository.save(examinationRequest);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<ExaminationRequestDetailsDTO>> getAll(Long id, int page) {
        Clinic clinic = this.clinicRepository.findById(id).orElseThrow(ClinicNotFoundException::new);

        Pageable pageable = PageRequest.of(page, 10);
        Page<ExaminationRequest> requests = this.examinationRequestRepository.findAllByClinic(id, pageable);

        ArrayList<ExaminationRequestDetailsDTO> requestDTOS = new ArrayList<>();
        for (ExaminationRequest er : requests) {
            if (er.getExaminationAppointment().getExaminationRoom() != null) continue;

            Doctor doc = this.doctorRepository.findById(er.getDoctorId()).orElseThrow(UserNotFoundException::new);
            Patient pat = this.patientRepository.findById(er.getPatientId()).orElseThrow(UserNotFoundException::new);
            DoctorDTO doctorDTO = DoctorDTO.builder().id(doc.getId())
                    .firstName(doc.getFirstName()).lastName(doc.getLastName()).build();;
            PatientDTO patientDTO = PatientDTO.builder().id(pat.getId()).firstName(pat.getFirstName())
                    .lastName(pat.getLastName()).build();

            requestDTOS.add(ExaminationRequestDetailsDTO.builder()
                .doctor(doctorDTO)
                .patient(patientDTO)
                .id(er.getId())
                .date(er.getExaminationAppointment().getStartDate())
                .duration(er.getExaminationAppointment().getDuration())
                .pages(requests.getTotalPages()).build());
        }
        return new ResponseEntity<>(requestDTOS, HttpStatus.OK);
    }


    public ResponseEntity<Void> reserve(Long requestId, Long roomId, DateTime nextAvailable) {
        ExaminationRequest examinationRequest = this.examinationRequestRepository.findById(requestId).orElseThrow(ExaminationRequestNotFoundException::new);

        ExaminationAppointment examinationAppointment = examinationRequest.getExaminationAppointment();

        ExaminationRoom examinationRoom = this.examinationRoomRepository.findById(roomId).orElseThrow(ExaminationRoomNotFoundException::new);
        TypeOfExamination typeOfExamination = this.typeOfExaminationRepository.findById(examinationRequest.getTypeId()).orElseThrow(TypeOfExaminationNotFoundException::new);

        Doctor doctor = this.doctorRepository.findById(examinationRequest.getDoctorId()).orElseThrow(UserNotFoundException::new);
        Patient patient = this.patientRepository.findById(examinationRequest.getPatientId()).orElseThrow(UserNotFoundException::new);

        Examination examination = new Examination();
        examination.setClinic(examinationRoom.getClinic());
        examination.setExaminationRoom(examinationRoom);


        examination.setPatient(patient);
        examination.setMedicalRecord(patient.getMedicalRecord());
        examination.setExaminationAppointment(examinationRequest.getExaminationAppointment());
        examinationRequest.getExaminationAppointment().setExaminationRoom(examinationRoom);
        examination.setType(typeOfExamination);

        if (examinationRequest.getExaminationAppointment().getStartDate().equals(nextAvailable)) {
            examination.setDoctor(doctor);
        } else {
            boolean doctorIsFree = checkIfDoctorIsFree(doctor, nextAvailable, examinationRequest.getExaminationAppointment().getDuration());
            if (doctorIsFree)
                examination.setDoctor(doctor);

            else {
                Doctor doc = findAvailableDoctor(nextAvailable, examinationAppointment.getDuration());
                if (doc != null) {
                    examination.setDoctor(doc);
                    examinationAppointment.setStartDate(nextAvailable);
                    examination.setExaminationAppointment(examinationAppointment);
                }
                else {
                    throw new NoAvailableDoctorsForExaminationException("Please select another time for appointment");
                }
            }
            examinationAppointment.setStartDate(nextAvailable);
            examinationAppointment.setEndDate(new DateTime(nextAvailable.getMillis() + examinationAppointment.getDuration().getMillis(), DateTimeZone.UTC));
        }
        examination.setExaminationAppointment(null);
        this.examinationRepository.save(examination);
        examinationAppointment.setExamination(examination);
        this.examinationAppointmentRepository.save(examinationAppointment);
        examinationRequest.setExaminationAppointment(null);
        this.examinationRequestRepository.delete(examinationRequest);

        examination.setExaminationAppointment(examinationAppointment);
        this.examinationRepository.save(examination);
    }

    private boolean checkIfDoctorIsFree(Doctor doc, DateTime nextAvailable, Duration duration) {
        List<ExaminationRequest> examinationRequests = this.examinationRequestRepository.findByDoctorId(doc.getId());
        DateTime nextEnd = new DateTime(nextAvailable.getMillis() + duration.getMillis(), DateTimeZone.UTC);

        for (ExaminationRequest er : examinationRequests) {
            ExaminationAppointment ea = er.getExaminationAppointment();
            ea.setEndDate(new DateTime(ea.getStartDate().getMillis() + ea.getDuration().getMillis(), DateTimeZone.UTC));
            if (areAppointmentsOverlapping(nextAvailable, nextEnd, ea.getEndDate(), ea.getStartDate()))
                return false;
        }

        List<VacationRequest> vacationRequests = this.vacationRequestRepository.findByDoc(doc, DateTime.now());
        if (vacationRequests.size() == 0) return true;

        for (VacationRequest vacationRequest : vacationRequests){
            if (areAppointmentsOverlapping(nextAvailable, nextEnd, vacationRequest.getEndDate(), vacationRequest.getStartDate()))
                return false;
        }

        return true;
    }

    private boolean areAppointmentsOverlapping(DateTime nextAvailable, DateTime nextEnd, DateTime endDate, DateTime startDate) {
        if (!nextAvailable.isAfter(endDate) && !nextEnd.isBefore(startDate))
            return true;
        if (nextAvailable.isBefore(startDate) && nextEnd.isAfter(endDate))
            return true;
        if (nextEnd.isBefore(endDate) && nextEnd.isAfter(startDate))
            return true;
        if (nextAvailable.isAfter(startDate) && nextAvailable.isBefore(endDate))
            return true;
        if (nextAvailable.isEqual(startDate) || nextEnd.isEqual(endDate))
            return true;
        return nextAvailable.isBefore(startDate) && nextEnd.isBefore(endDate);
    }

    private Doctor findAvailableDoctor(DateTime nextAvailable, Duration duration) {
        List<Doctor> doctors = this.doctorRepository.findAll();
        for (Doctor doc : doctors) {
            if (checkIfDoctorIsFree(doc, nextAvailable, duration))
                return doc;
        }
        return null;
    }

    @Scheduled(cron = "59 23 * * * *")
    private void reserveAll() {
        List<Clinic> clinics = this.clinicRepository.findAll();

        for (Clinic clinic : clinics) {
            List<ExaminationRequest> examinationRequests = this.examinationRequestRepository.findByClinic(clinic);
            List<ExaminationRoom> examinationRooms = this.examinationRoomRepository.findByClinic(clinic);

            for (ExaminationRequest eRequest : examinationRequests) {
                HashMap<ExaminationRoom, DateTime> roomTime = new HashMap<>();
                for (ExaminationRoom eRoom : examinationRooms) {
                    DateTime earliestInRoom = this.roomsService.findFirstAvailableForExamination(eRoom, eRequest.getExaminationAppointment().getStartDate(), eRequest.getExaminationAppointment().getDuration());
                    roomTime.put(eRoom, earliestInRoom);
                }
                Map.Entry<ExaminationRoom, DateTime> min = null;
                for (Map.Entry<ExaminationRoom, DateTime> entry : roomTime.entrySet()) {
                    if (min == null || min.getValue().isAfter(entry.getValue())) {
                        min = entry;
                    }
                }
                try {
                    assert min != null;
                    reservation(eRequest, min.getKey(), min.getValue());
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
