package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.dto.OperationRequestDTO;
import com.ProjectCC.dero.dto.OperationRoomRequestDTO;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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


    @Autowired
    public OperationRequestService(ExaminationRequestRepository examinationRequestRepository,OperationRoomRepository operationRoomRepository,OperationRepository operationRepository,OperationAppointmentRepository operationAppointmentRepository,UserRepository userRepository, OperationRequestRepository operationRequestRepository, ModelMapper modelMapper) {
        this.operationRequestRepository = operationRequestRepository;
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
        MedicalStaff med = (MedicalStaff) userRepository.findByEmail(username);

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
        }

        operation.setDoctors(doctors);
        operation.setOperationAppointment(oa);
        oa.setOperation(operation);

        this.operationRepository.save(operation);
        this.operationRequestRepository.delete(operationRequest);
        this.operationAppointmentRepository.save(oa);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean checkIfDoctorIsFree(Doctor doc, DateTime nextAvailable, Duration duration) {
        List<ExaminationRequest> examinationRequests = this.examinationRequestRepository.findByDoctorId(doc.getId());
        List<Operation> operations = this.operationRepository.findByDoctorsId(doc.getId());
        DateTime nextEnd = new DateTime(nextAvailable.getMillis() + duration.getMillis());

        for (ExaminationRequest er : examinationRequests) {
            ExaminationAppointment ea = er.getExaminationAppointment();
            ea.setEndDate(new DateTime(ea.getStartDate().getMillis() + ea.getDuration().getMillis(), DateTimeZone.UTC));
            if (!nextAvailable.isAfter(ea.getEndDate()) && !nextEnd.isBefore(ea.getStartDate())) {
                return false;
            }
        }

        for(Operation o: operations){
            DateTime end = (new DateTime(o.getDate().getMillis()+o.getDuration().getMillis(), DateTimeZone.UTC));
            if (!nextAvailable.isAfter(end) && !nextEnd.isBefore(o.getDate())) {
                return false;
            }
        }

        Doctor doctor = (Doctor) userRepository.findById(doc.getId()).orElseGet(null);
        for(VacationRequest v: doctor.getVacationRequest()){
            DateTime start = (new DateTime(v.getStartDate().getMillis(),DateTimeZone.UTC));
            DateTime end = (new DateTime(v.getEndDate().getMillis(),DateTimeZone.UTC));
            if(!nextAvailable.isBefore(start) && !nextAvailable.isAfter(end))
                return false;
        }
        return true;

    }
}
