package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.dto.OperationRequestDTO;
import com.ProjectCC.dero.dto.OperationRoomRequestDTO;
import com.ProjectCC.dero.exception.UserNotFoundException;
import com.ProjectCC.dero.mail.SmtpMailSender;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import com.ProjectCC.dero.repository.OperationAppointmentRepository;
import com.ProjectCC.dero.repository.OperationRequestRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class OperationRequestService {

    private OperationRequestRepository operationRequestRepository;
    private ExaminationRequestRepository examinationRequestRepository;
    private OperationRepository operationRepository;
    private OperationAppointmentRepository operationAppointmentRepository;
    private UserRepository userRepository;
    private OperationRoomRepository operationRoomRepository;
    private ModelMapper modelMapper;
    private RoomsService roomsService;
    private ClinicRepository clinicRepository;
    private DoctorRepository doctorRepository;
    private SmtpMailSender smtpMailSender;

    @Autowired
    public OperationRequestService(DoctorRepository doctorRepository,ClinicRepository clinicRepository,RoomsService roomsService,ExaminationRequestRepository examinationRequestRepository,
                                   OperationRoomRepository operationRoomRepository,OperationRepository operationRepository,OperationAppointmentRepository operationAppointmentRepository,
                                   UserRepository userRepository,SmtpMailSender smtpMailSender, OperationRequestRepository operationRequestRepository, ModelMapper modelMapper) {
        this.operationRequestRepository = operationRequestRepository;
        this.roomsService = roomsService;
        this.smtpMailSender = smtpMailSender;
        this.clinicRepository = clinicRepository;
        this.doctorRepository = doctorRepository;
        this.operationAppointmentRepository = operationAppointmentRepository;
        this.examinationRequestRepository = examinationRequestRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
        this.operationRoomRepository = operationRoomRepository;
    }

    public ResponseEntity<Void> save(OperationRequestDTO operationRequestDTO) {
        OperationRequest operationRequest = modelMapper.map(operationRequestDTO, OperationRequest.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MedicalStaff med = (MedicalStaff) userRepository.findById(operationRequestDTO.getDoctorId()).orElseGet(null);
        operationRequest.setClinic(med.getClinic());
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Void> reserveOperation(OperationRoomRequestDTO operationRoomRequest) throws MessagingException {
        Optional<OperationRequest> optionalOperationRequest = this.operationRequestRepository.findById(operationRoomRequest.getRequestId());
        if(!optionalOperationRequest.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        OperationRequest operationRequest = optionalOperationRequest.get();

        OperationAppointment oa = new OperationAppointment();
        oa.setDuration(operationRequest.getDuration());
        oa.setStartDate(operationRequest.getDate());
        oa.setEndDate(new DateTime(oa.getStartDate().getMillis() + oa.getDuration().getMillis(), DateTimeZone.UTC));
        OperationRoom or =  operationRoomRepository.findById(operationRoomRequest.getRoom().getId()).orElseGet(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ClinicAdministrator ca = (ClinicAdministrator) userRepository.findByEmail(username);

        oa.setOperationRoom(or);
        oa.setClinic(ca.getClinic());

        Operation operation = new Operation();
        operation.setClinic(ca.getClinic());
        operation.setDate(oa.getStartDate());
        operation.setDuration(oa.getDuration());
        Patient patient = (Patient) userRepository.findById(operationRequest.getPatientId()).orElseGet(null);
        operation.setPatient(patient);
        operation.setMedicalRecord(patient.getMedicalRecord());
        operation.setOperationRoom(or);
        Set<Doctor> doctors = new HashSet<>();
        for(DoctorDTO d: operationRoomRequest.getDoctors()){
            Doctor doctor = (Doctor) userRepository.findById(d.getId()).orElseGet(null);
            if(checkIfDoctorIsFree(doctor,oa.getStartDate(),oa.getDuration()))
                doctors.add(doctor);
            smtpMailSender.send(doctor.getEmail(),"Operation", "Your have been scheduled:<br> Date:"+operation.getDate()+"<br> Room:"+operation.getDuration());
        }

        operation.setDoctors(doctors);
        operation.setOperationAppointment(oa);
        oa.setOperation(operation);

        smtpMailSender.send(patient.getEmail(),"Operation", "Your operation is scheduled to:<br> Date:"+operation.getDate()+"<br> Room:"+operation.getDuration());

        this.operationRepository.save(operation);
        this.operationRequestRepository.delete(operationRequest);
        this.operationAppointmentRepository.save(oa);


        return new ResponseEntity<>(HttpStatus.OK);
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
    public boolean checkIfDoctorIsFree(Doctor doc, DateTime nextAvailable, Duration duration) {
        List<ExaminationRequest> examinationRequests = this.examinationRequestRepository.findByDoctorId(doc.getId());
        List<Operation> operations = this.operationRepository.findByDoctorsId(doc.getId());
        DateTime nextEnd = new DateTime(nextAvailable.getMillis() + duration.getMillis());

        for (ExaminationRequest er : examinationRequests) {
            ExaminationAppointment ea = er.getExaminationAppointment();
            ea.setEndDate(new DateTime(ea.getStartDate().getMillis() + ea.getDuration().getMillis(), DateTimeZone.UTC));
            if (areAppointmentsOverlapping(nextAvailable, nextEnd, ea.getEndDate(), ea.getStartDate())) {
                return false;
            }
        }

        for(Operation o: operations){
            DateTime end = (new DateTime(o.getDate().getMillis()+o.getDuration().getMillis(), DateTimeZone.UTC));
            DateTime endDate = (new DateTime(o.getDate().getMillis() + o.getDuration().getMillis(), DateTimeZone.UTC));
            if (areAppointmentsOverlapping(nextAvailable, nextEnd, endDate, o.getDate())) {
                return false;
            }
        }

        Doctor doctor = doctorRepository.findById(doc.getId()).orElseGet(null);
        if(doctor.getVacationRequest().size() == 0) return  true;
        for(VacationRequest v: doctor.getVacationRequest()){
            DateTime start = (new DateTime(v.getStartDate().getMillis(),DateTimeZone.UTC));
            DateTime end = (new DateTime(v.getEndDate().getMillis(),DateTimeZone.UTC));
            if(areAppointmentsOverlapping(nextAvailable, nextEnd, v.getEndDate(), v.getStartDate()))
                return false;
        }
        return true;

    }

    private void reservation(OperationRequest operationRequest, OperationRoom operationRoom, DateTime nextAvailable) throws UserNotFoundException, MessagingException {
        Optional<User> optionalPatient = this.userRepository.findById(operationRequest.getPatientId());
        Optional<OperationRequest> optionalOperationRequest = this.operationRequestRepository.findById(operationRequest.getId());

        if (!optionalPatient.isPresent())
            throw new UserNotFoundException();

        if(!optionalOperationRequest.isPresent())
            return;
        OperationRequest operationRequest1 = optionalOperationRequest.get();


        OperationAppointment oa = new OperationAppointment();
        oa.setDuration(operationRequest.getDuration());
        oa.setStartDate(operationRequest.getDate());
        oa.setEndDate(new DateTime(oa.getStartDate().getMillis() + oa.getDuration().getMillis(), DateTimeZone.UTC));
        OperationRoom or =  operationRoomRepository.findById(operationRoom.getId()).orElseGet(null);

        oa.setOperationRoom(or);
        oa.setClinic(operationRoom.getClinic());

        Operation operation = new Operation();
        operation.setClinic(operationRoom.getClinic());
        operation.setDate(oa.getStartDate());
        operation.setDuration(oa.getDuration());
        Patient patient = (Patient) userRepository.findById(operationRequest.getPatientId()).orElseGet(null);
        operation.setPatient(patient);
        operation.setMedicalRecord(patient.getMedicalRecord());
        operation.setOperationRoom(or);

        Set<Doctor> doctors = new HashSet<>();
        Doctor doc = findAvailableDoctor(nextAvailable, operationRequest.getDuration());
        if (doc != null) {
            doctors.add(doc);
            smtpMailSender.send(doc.getEmail(),"Operation", "Your have been scheduled:<br> Date:"+operation.getDate()+"<br> Room:"+operation.getDuration());
        }

        operation.setDoctors(doctors);
        operation.setOperationAppointment(oa);
        oa.setOperation(operation);

        smtpMailSender.send(patient.getEmail(),"Operation", "Your operation is scheduled to:<br> Date:"+operation.getDate()+"<br> Room:"+operation.getDuration());


        this.operationRepository.save(operation);
        this.operationRequestRepository.delete(operationRequest);
        this.operationAppointmentRepository.save(oa);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    private Doctor findAvailableDoctor(DateTime nextAvailable, Duration duration) {
        List<Doctor> doctors = this.doctorRepository.findAll();
        for (Doctor doc : doctors) {
            if (checkIfDoctorIsFree(doc, nextAvailable, duration))
                return doc;
        }
        return null;
    }

    @Scheduled(cron = "59 59 23 * * *")
    private void reserveAll() {
        List<Clinic> clinics = this.clinicRepository.findAll();

        for (Clinic clinic : clinics) {
            List<OperationRequest> operationRequests = this.operationRequestRepository.findByClinicAll(clinic);
            List<OperationRoom> operationRooms = this.operationRoomRepository.findByClinic(clinic.getId());

            for (OperationRequest oRequest : operationRequests) {
                HashMap<OperationRoom, DateTime> roomTime = new HashMap<>();
                for (OperationRoom oRoom : operationRooms) {
                    DateTime earliestInRoom = this.roomsService.findFirstAvailableForOperation(oRoom, oRequest.getDate(), oRequest.getDuration());
                    roomTime.put(oRoom, earliestInRoom);
                }
                Map.Entry<OperationRoom, DateTime> min = null;
                for (Map.Entry<OperationRoom, DateTime> entry : roomTime.entrySet()) {
                    if (min == null || min.getValue().isAfter(entry.getValue())) {
                        min = entry;
                    }
                }
                try {
                    assert min != null;
                    reservation(oRequest, min.getKey(), min.getValue());
                } catch (UserNotFoundException | MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
