package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.dto.ExaminationRequestDTO;
import com.ProjectCC.dero.dto.ExaminationRequestDetailsDTO;
import com.ProjectCC.dero.dto.PatientDTO;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminationRequestService {

    private ExaminationRequestRepository examinationRequestRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ExaminationRoomRepository examinationRoomRepository;
    private ExaminationRepository examinationRepository;
    private ClinicRepository clinicRepository;
    private TypeOfExaminationRepository typeOfExaminationRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ExaminationRequestService(ExaminationRequestRepository examinationRequestRepository, DoctorRepository doctorRepository,
                                     PatientRepository patientRepository, ExaminationRoomRepository examinationRoomRepository,
                                     ExaminationRepository examinationRepository, ClinicRepository clinicRepository,
                                     TypeOfExaminationRepository typeOfExaminationRepository, ModelMapper modelMapper) {
        this.examinationRequestRepository = examinationRequestRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.examinationRoomRepository = examinationRoomRepository;
        this.examinationRepository = examinationRepository;
        this.clinicRepository = clinicRepository;
        this.typeOfExaminationRepository = typeOfExaminationRepository;
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
        Clinic clinic = this.clinicRepository.findById(id).orElseThrow(ClinicNotFoundException::new);

        Pageable pageable = PageRequest.of(page, 10);
        Page<ExaminationRequest> requests = this.examinationRequestRepository.findAllByClinic(clinic.getId(), pageable);

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
                Doctor doc = findAvailableDoctor(nextAvailable, examinationRequest.getExaminationAppointment().getDuration());
                if (doc != null)
                    examination.setDoctor(doc);
                else {
                    throw new NoAvailableDoctorsForExaminationException("Please select another time for appointment");
                }
            }
            examinationAppointment.setStartDate(nextAvailable);
            examinationAppointment.setEndDate(new DateTime(nextAvailable.getMillis() + examinationAppointment.getDuration().getMillis(), DateTimeZone.UTC));
        }
        examination.getExaminationAppointment().setExaminationRequest(null);
        this.examinationRepository.save(examination);
        examinationRequest.setExaminationAppointment(null);
        this.examinationRequestRepository.delete(examinationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean checkIfDoctorIsFree(Doctor doc, DateTime nextAvailable, Duration duration) {
        List<ExaminationRequest> examinationRequests = this.examinationRequestRepository.findByDoctorId(doc.getId());
        DateTime nextEnd = new DateTime(nextAvailable.getMillis() + duration.getMillis(), DateTimeZone.UTC);

        for (ExaminationRequest er : examinationRequests) {
            ExaminationAppointment ea = er.getExaminationAppointment();
            ea.setEndDate(new DateTime(ea.getStartDate().getMillis() + ea.getDuration().getMillis(), DateTimeZone.UTC));
            if (!nextAvailable.isAfter(ea.getEndDate()) && !nextEnd.isBefore(ea.getStartDate())) {
                return false;
            }
        }
        return true;
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
