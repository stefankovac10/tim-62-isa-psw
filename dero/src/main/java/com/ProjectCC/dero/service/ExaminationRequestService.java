package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.*;
import com.ProjectCC.dero.exceptions.*;
import com.ProjectCC.dero.exception.BadExaminationRequest;
import com.ProjectCC.dero.exception.UserNotFoundException;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private ModelMapper modelMapper;
    private ExaminationAppointmentRepository examinationAppointmentRepository;
    private RoomsService roomsService;
    private VacationRequestRepository vacationRequestRepository;
    private TypeOfExaminationRepository typeOfExaminationRepository;

    @Autowired
    public ExaminationRequestService(ExaminationRequestRepository examinationRequestRepository, DoctorRepository doctorRepository,
                                     PatientRepository patientRepository, ExaminationRoomRepository examinationRoomRepository,
                                     ExaminationRepository examinationRepository, ModelMapper modelMapper, ClinicRepository clinicRepository,
                                     ExaminationAppointmentRepository examinationAppointmentRepository, RoomsService roomsService,
                                     TypeOfExaminationRepository typeOfExaminationRepository, VacationRequestRepository vacationRequestRepository) {
        this.examinationRequestRepository = examinationRequestRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.examinationRoomRepository = examinationRoomRepository;
        this.examinationRepository = examinationRepository;
        this.clinicRepository = clinicRepository;
        this.modelMapper = modelMapper;
        this.examinationAppointmentRepository = examinationAppointmentRepository;
        this.roomsService = roomsService;
        this.vacationRequestRepository = vacationRequestRepository;
        this.typeOfExaminationRepository = typeOfExaminationRepository;
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
        Page<ExaminationRequest> requests = this.examinationRequestRepository.findAllByClinic(clinic.getId(), pageable);

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


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Void> reserve(Long requestId, Long roomId, DateTime nextAvailable) {
        ExaminationRequest examinationRequest = this.examinationRequestRepository.findByRequestId(requestId);
        if (examinationRequest == null) throw new ExaminationRequestNotFoundException();

        ExaminationAppointment examinationAppointment = examinationRequest.getExaminationAppointment();

        ExaminationRoom examinationRoom = this.examinationRoomRepository.findById(roomId).orElseThrow(ExaminationRoomNotFoundException::new);
        TypeOfExamination typeOfExamination = this.typeOfExaminationRepository.findById(examinationRequest.getTypeId()).orElseThrow(TypeOfExaminationNotFoundException::new);

        Doctor doctor = this.doctorRepository.findById(examinationRequest.getDoctorId()).orElseThrow(UserNotFoundException::new);
        Patient patient = this.patientRepository.findById(examinationRequest.getPatientId()).orElseThrow(UserNotFoundException::new);

        try {
            reservation(examinationRequest, examinationRoom, nextAvailable);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void reservation(ExaminationRequest examinationRequest, ExaminationRoom examinationRoom, DateTime nextAvailable) throws UserNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(examinationRequest.getDoctorId());
        Optional<Patient> optionalPatient = this.patientRepository.findById(examinationRequest.getPatientId());
        Optional<ExaminationAppointment> optionalExaminationAppointment = this.examinationAppointmentRepository.findById(examinationRequest.getExaminationAppointment().getId());
        Optional<TypeOfExamination> optionalTypeOfExamination = this.typeOfExaminationRepository.findById(examinationRequest.getTypeId());

        if (!optionalDoctor.isPresent() || !optionalPatient.isPresent())
            throw new UserNotFoundException();
        if (!optionalExaminationAppointment.isPresent() || !optionalTypeOfExamination.isPresent())
            throw new BadExaminationRequest("Some required fields are missing");

        ExaminationAppointment examinationAppointment = optionalExaminationAppointment.get();

        Examination examination = new Examination();
        examination.setClinic(examinationRoom.getClinic());
        examination.setExaminationRoom(examinationRoom);
        examination.setPatient(optionalPatient.get());
        examination.setMedicalRecord(optionalPatient.get().getMedicalRecord());
        examination.setExaminationAppointment(examinationAppointment);
        examinationAppointment.setExaminationRoom(examinationRoom);
        examination.setType(optionalTypeOfExamination.get());


        if (examinationAppointment.getStartDate().equals(nextAvailable)) {
            examination.setDoctor(optionalDoctor.get());
            examination.setExaminationAppointment(examinationAppointment);
        } else {
            boolean doctorIsFree = checkIfDoctorIsFree(optionalDoctor.get(), nextAvailable, examinationAppointment.getDuration());
            if (doctorIsFree) {
                examination.setDoctor(optionalDoctor.get());
                examinationAppointment.setStartDate(nextAvailable);
                examination.setExaminationAppointment(examinationAppointment);
            }
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

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean checkIfDoctorIsFree(Doctor doc, DateTime nextAvailable, Duration duration) {
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

    @Transactional(propagation = Propagation.REQUIRED)
    public Doctor findAvailableDoctor(DateTime nextAvailable, Duration duration) {

        List<Doctor> doctors = this.doctorRepository.findAll();
        for (Doctor doc : doctors) {
            if (checkIfDoctorIsFree(doc, nextAvailable, duration))
                return doc;
        }
        return null;
    }

    /*
    // duration mi je u minutama ...
    public ResponseEntity<ExaminationRequestDTO> saveExaminationRequest(Long doctorID, String email, String timeString, int duration, String date) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorID);
        if(!doctorOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Doctor doctor = doctorOptional.get();

        Patient patient = patientRepository.findByEmail(email);
        Long patientID = patient.getId();

        LocalTime time = LocalTime.parse(timeString);
        int hours = time.getHourOfDay();
        int minutes = time.getMinuteOfHour();

        DateTime startDate = DateTime.parse(date).plusHours(hours).plusMinutes(minutes);
        DateTime endDate = DateTime.parse(date).plusHours(hours).plusMinutes(minutes).plusMinutes(duration);

        Optional<Clinic> clinicOptional = clinicRepository.findById(doctor.getClinic().getId());
        if(!clinicOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Clinic clinic = clinicOptional.get();

        Examination examination = new Examination();
        examination.setType(doctor.getSpecialisedType());
        examination.setClinic(clinic);
        examination.setDoctor(doctor);
        examination.setPatient(patient);

        ExaminationAppointment examinationAppointment = new ExaminationAppointment();
        examinationAppointment.setStartDate(startDate);
        examinationAppointment.setEndDate(endDate);
        Duration duration1 = new Duration(duration * 60000);
        examinationAppointment.setDuration(duration1);
        examinationAppointment.setExamination(examination);

        ExaminationRequest examinationRequest = new ExaminationRequest();
        examinationRequest.setDoctorId(doctorID);
        examinationRequest.setPatientId(patientID);
        examinationRequest.setTypeId(doctor.getSpecialisedType().getId());
        examinationRequest.setExaminationAppointment(examinationAppointment);

        examinationRequest = this.examinationRequestRepository.save(examinationRequest);

        if(examinationRequest != null) {
            //ExaminationRequestDTO examinationRequestDTO = modelMapper()
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
     */

    // duration mi je u minutama ...
    public ResponseEntity<CustomExaminationRequestDTO> saveExaminationRequest(CustomExaminationRequestDTO customExaminationRequestDTO) {
        Long doctorID = customExaminationRequestDTO.getDoctorID();
        String email = customExaminationRequestDTO.getEmail();
        String timeString = customExaminationRequestDTO.getTime();
        String date = customExaminationRequestDTO.getDate();
        int duration = customExaminationRequestDTO.getDuration();

        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorID);
        if(!doctorOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Doctor doctor = doctorOptional.get();

        Patient patient = patientRepository.findByEmail(email);
        Long patientID = patient.getId();

        LocalTime time = LocalTime.parse(timeString);
        int hours = time.getHourOfDay();
        int minutes = time.getMinuteOfHour();

        DateTime startDate = DateTime.parse(date).plusHours(hours).plusMinutes(minutes);
        DateTime endDate = DateTime.parse(date).plusHours(hours).plusMinutes(minutes).plusMinutes(duration);

        Optional<Clinic> clinicOptional = clinicRepository.findById(doctor.getClinic().getId());
        if(!clinicOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Clinic clinic = clinicOptional.get();

        Examination examination = new Examination();
        examination.setType(doctor.getSpecialisedType());
        examination.setClinic(clinic);
        examination.setDoctor(doctor);
        examination.setPatient(patient);

        ExaminationAppointment examinationAppointment = new ExaminationAppointment();
        examinationAppointment.setStartDate(startDate);
        examinationAppointment.setEndDate(endDate);
        Duration duration1 = new Duration(duration * 60000);
        examinationAppointment.setDuration(duration1);
        examinationAppointment.setExamination(examination);

        ExaminationRequest examinationRequest = new ExaminationRequest();
        examinationRequest.setDoctorId(doctorID);
        examinationRequest.setPatientId(patientID);
        examinationRequest.setTypeId(doctor.getSpecialisedType().getId());
        examinationRequest.setExaminationAppointment(examinationAppointment);

        examinationRequest = this.examinationRequestRepository.save(examinationRequest);

        if(examinationRequest != null) {
            //ExaminationRequestDTO examinationRequestDTO = modelMapper()
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @Scheduled(cron = "0 59 23 * * *")
    public void reserveAll() {
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