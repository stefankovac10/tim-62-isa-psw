package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private UserRepository userRepository;
    private ClinicService clinicService;
    private TypeOfExaminationService typeOfExaminationService;
    private ExaminationRequestRepository examinationRequestRepository;
    private ExaminationRepository examinationRepository;
    private OperationRepository operationRepository;
    private ModelMapper modelMapper;
    private OperationRequestRepository operationRequestRepository;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;
    private ClinicRepository clinicRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, OperationRepository operationRepository, ClinicService clinicService,UserRepository userRepository,
                         TypeOfExaminationService typeOfExaminationService, ModelMapper modelMapper,
                         PasswordEncoder passwordEncoder,
                         OperationRequestRepository operationRequestRepository,ExaminationRequestRepository examinationRequestRepository, AuthorityService authorityService) {

        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.operationRequestRepository = operationRequestRepository;
        this.operationRepository = operationRepository;
        this.examinationRequestRepository = examinationRequestRepository;
        this.clinicService = clinicService;
        this.clinicRepository = clinicRepository;
        this.typeOfExaminationService = typeOfExaminationService;
        this.modelMapper = modelMapper;
        this.examinationRepository = examinationRepository;
        this.operationRepository = operationRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    public ResponseEntity<DoctorDTO> save(DoctorDTO doctorDTO, String type) {
        TypeOfExamination typeOfExamination = this.typeOfExaminationService.findByName(type);
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        doctor.setSpecialisedType(typeOfExamination);

        doctor.setPassword(passwordEncoder.encode(doctorDTO.getPassword()));
        List<Authority> authorities = authorityService.findByName("ROLE_DOCTOR");
        doctor.setAuthorities(authorities);

        Clinic clinic = clinicService.findOne((long) 1);
        doctor.setClinic(clinic);

        doctor = doctorRepository.save(doctor);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // deletes doctor if he does not have scheduled examinations
    public ResponseEntity<String> delete(Long id) {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(id);
        if (!optionalDoctor.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Doctor doc = optionalDoctor.get();

        List<Examination> examinations = this.examinationRepository.findByDoctor(doc);
        DateTime now = new DateTime();

        // removes doctor from past examinations and returns error if doctor has scheduled examinations
        for (Examination examination: examinations) {
            if (examination.getExaminationAppointment().getStartDate().isAfter(now))
                return new ResponseEntity<>("Doctor has reserved examinations", HttpStatus.BAD_REQUEST);
            examination.setDoctor(null);
            this.examinationRepository.save(examination);
        }

        // removes doctor from all operations
        for (Operation op : doc.getOperations()) {
            op.getDoctors().remove(doc);
            this.operationRepository.save(op);
        }

        doctorRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<DoctorDTO>> findAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorsDTO = new ArrayList<>();

        for (Doctor d : doctors) {
            doctorsDTO.add(DoctorDTO.builder()
                .id(d.getId())
                .firstName(d.getFirstName())
                .lastName(d.getLastName())
                .jmbg(d.getJmbg())
                .country(d.getCountry())
                .city(d.getCity())
                .address(d.getAddress())
                .telephone(d.getTelephone())
                .clinic(ClinicDTO.builder().name(d.getClinic().getName()).build())
                .build());
        }

        return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<DoctorDTO>> searchDoctors(String firstName,
                                                         String lastName,
                                                         String email,
                                                         String city,
                                                         String country,
                                                         String clinic){
        firstName = "%" + firstName + "%";
        lastName = "%" + lastName + "%";
        email = "%" + email + "%";
        city = "%" + city + "%";
        country = "%" + country + "%";
        clinic = "%" + clinic + "%";

        List<Doctor> doctors = doctorRepository.searchDoctors(firstName, lastName, email, city, country, clinic);
        List<DoctorDTO> doctorsDTO = new ArrayList<>();

        for (Doctor d : doctors) {
            doctorsDTO.add(DoctorDTO.builder()
                    .firstName(d.getFirstName())
                    .lastName(d.getLastName())
                    .email(d.getEmail())
                    .city(d.getCity())
                    .country(d.getCountry())
                    .clinic(ClinicDTO.builder().name(d.getClinic().getName()).build())
                    .build());
        }

        return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
    }


    public ResponseEntity<List<DoctorDTO>> findAvaliable(Long id, String next) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ClinicAdministrator cadmin = (ClinicAdministrator) userRepository.findByEmail(username);

        OperationRequest or = operationRequestRepository.findById(id).orElseGet(null);
        List<Doctor> doctors = doctorRepository.findAllByClinic(cadmin.getClinic().getId());
        List<DoctorDTO> doctorsDTO = new ArrayList<>();

        for (Doctor d : doctors) {
            if(checkIfDoctorIsFree(d, DateTime.parse(next), or.getDuration())) {
                doctorsDTO.add(DoctorDTO.builder()
                        .id(d.getId())
                        .firstName(d.getFirstName())
                        .lastName(d.getLastName())
                        .jmbg(d.getJmbg())
                        .country(d.getCountry())
                        .city(d.getCity())
                        .address(d.getAddress())
                        .telephone(d.getTelephone())
                        .clinic(ClinicDTO.builder().name(d.getClinic().getName()).build())
                        .build());
            }
        }

        return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
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
        return true;
 
    }

    public ResponseEntity<List<DoctorDTO>> getDoctorsFromClinic(Long id) {
        Optional<Clinic> optional = this.clinicRepository.findById(id);
        if (!optional.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Clinic clinic = optional.get();
        List<Doctor> doctors = this.doctorRepository.findByClinic(clinic);

        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for (Doctor d : doctors) {
            doctorDTOS.add(DoctorDTO.builder()
                    .firstName(d.getFirstName())
                    .lastName(d.getLastName())
                    .id(d.getId())
                    .build());
        }

        return new ResponseEntity<>(doctorDTOS, HttpStatus.OK);

    }
}
