package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.*;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationRequestService {

    private ExaminationRequestRepository examinationRequestRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ExaminationRoomRepository examinationRoomRepository;
    private ExaminationRepository examinationRepository;
    private ClinicRepository clinicRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ExaminationRequestService(ExaminationRequestRepository examinationRequestRepository, DoctorRepository doctorRepository,
                                     PatientRepository patientRepository, ExaminationRoomRepository examinationRoomRepository,
                                     ExaminationRepository examinationRepository, ClinicRepository clinicRepository, ModelMapper modelMapper) {
        this.examinationRequestRepository = examinationRequestRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.examinationRoomRepository = examinationRoomRepository;
        this.examinationRepository = examinationRepository;
        this.clinicRepository = clinicRepository;
        this.modelMapper = modelMapper;
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
        Optional<Clinic> optionalClinic = this.clinicRepository.findById(id);
        if (!optionalClinic.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Clinic clinic = optionalClinic.get();
        Pageable pageable = PageRequest.of(page, 10);
        Page<ExaminationRequest> requests = this.examinationRequestRepository.findAllByClinic(clinic, pageable);

        ArrayList<ExaminationRequestDetailsDTO> requestDTOS = new ArrayList<>();
        for (ExaminationRequest er : requests) {
            if (er.getExaminationAppointment().getExaminationRoom() != null) continue;

            Optional<Doctor> doc = this.doctorRepository.findById(er.getDoctorId());
            Optional<Patient> pat = this.patientRepository.findById(er.getPatientId());
            DoctorDTO doctorDTO = null;
            PatientDTO patientDTO = null;
            if (doc.isPresent() && pat.isPresent()) {
                doctorDTO = DoctorDTO.builder().id(doc.get().getId())
                        .firstName(doc.get().getFirstName()).lastName(doc.get().getLastName()).build();
                patientDTO = PatientDTO.builder().id(pat.get().getId()).firstName(pat.get().getFirstName())
                        .lastName(pat.get().getLastName()).build();
            }
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
        Optional<ExaminationRequest> optionalExaminationRequest = this.examinationRequestRepository.findById(requestId);
        if (!optionalExaminationRequest.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ExaminationRequest examinationRequest = optionalExaminationRequest.get();

        Optional<ExaminationRoom> optionalExaminationRoom = this.examinationRoomRepository.findById(roomId);
        if (!optionalExaminationRoom.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ExaminationRoom examinationRoom = optionalExaminationRoom.get();

        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(examinationRequest.getDoctorId());
        Optional<Patient> optionalPatient = this.patientRepository.findById(examinationRequest.getPatientId());

        if (!optionalDoctor.isPresent() || !optionalPatient.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Examination examination = new Examination();
        examination.setClinic(examinationRoom.getClinic());
        examination.setExaminationRoom(examinationRoom);
        examination.setPatient(optionalPatient.get());
        examination.setMedicalRecord(optionalPatient.get().getMedicalRecord());
        examination.setExaminationAppointment(examinationRequest.getExaminationAppointment());
        examinationRequest.getExaminationAppointment().setExaminationRoom(examinationRoom);

//        examination.setType();

        if (examinationRequest.getExaminationAppointment().getStartDate().equals(nextAvailable)) {
            examination.setDoctor(optionalDoctor.get());
            examination.setExaminationAppointment(examinationRequest.getExaminationAppointment());
        } else {
            boolean doctorIsFree = checkIfDoctorIsFree(optionalDoctor.get(), nextAvailable, examinationRequest.getExaminationAppointment().getDuration());
            if (doctorIsFree)
                examination.setDoctor(optionalDoctor.get());
            else {
                Doctor doc = findAvailableDoctor(nextAvailable, examinationRequest.getExaminationAppointment().getDuration());
                if (doc != null)
                    examination.setDoctor(doc);
                else {
//                  doc = docWithFirstNextApp()
                }
            }
        }
        examination.getExaminationAppointment().setExaminationRequest(null);
        this.examinationRepository.save(examination);
        examinationRequest.setExaminationAppointment(null);
        this.examinationRequestRepository.delete(examinationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean checkIfDoctorIsFree(Doctor doc, DateTime nextAvailable, Duration duration) {
        List<ExaminationRequest> examinationRequests = this.examinationRequestRepository.findByDoctorId(doc.getId());
        DateTime nextEnd = new DateTime(nextAvailable.getMillis() + duration.getMillis());

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
}
