package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicDTO;
import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.repository.ExaminationRepository;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.xml.ws.Response;
import java.util.*;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private ClinicService clinicService;
    private TypeOfExaminationService typeOfExaminationService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;
    private ClinicRepository clinicRepository;
    private ExaminationRepository examinationRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, ClinicService clinicService,
                         TypeOfExaminationService typeOfExaminationService, ModelMapper modelMapper,
                         PasswordEncoder passwordEncoder, AuthorityService authorityService,
                         ClinicRepository clinicRepository, ExaminationRepository examinationRepository) {
        this.doctorRepository = doctorRepository;
        this.clinicService = clinicService;
        this.typeOfExaminationService = typeOfExaminationService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.clinicRepository = clinicRepository;
        this.examinationRepository = examinationRepository;
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

    public ResponseEntity<Void> delete(Long id) {
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

    public ResponseEntity<List<DoctorDTO>> getDoctorsByClinicAndTypeAndDate(Long clinicID, Long typeID, String date) {
        Optional<Clinic> clinicOptional = clinicRepository.findById(clinicID);
        if (!clinicOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Clinic clinic = clinicOptional.get();
        List<Doctor> doctors = doctorRepository.findByClinic(clinic);
        List<DoctorDTO> doctorDTOS = new ArrayList<>();

        DateTime startDate = DateTime.parse(date).plusHours(7);
        DateTime endDate = DateTime.parse(date).plusHours(19);
        List<Examination> examinations = examinationRepository.findByClinicAndDate(clinic, startDate, endDate);

        HashMap<Doctor, List<ExaminationAppointment>> doctorsAppointments = new HashMap<>();

        for (Examination e : examinations) {
            if (doctorsAppointments.containsKey(e.getDoctor())) {
                doctorsAppointments.get(e.getDoctor()).add(e.getExaminationAppointment());
            }
            else {
                doctorsAppointments.put(e.getDoctor(), new ArrayList<>());
                doctorsAppointments.get(e.getDoctor()).add(e.getExaminationAppointment());
            }
        }

        Set<Doctor> keys = doctorsAppointments.keySet();
        for (Doctor key : keys) {
            doctorsAppointments.get(key).sort((ea1, ea2) -> {
                if (ea1.getStartDate() == null || ea2.getStartDate() == null)
                    return 0;
                return ea1.getStartDate().compareTo(ea2.getStartDate());
            });
        }

        for (Doctor d : doctors) {
            if (d.getSpecialisedType().getId().equals(typeID) && clinicService.isDoctorAbleToPerformAnExamination(doctorsAppointments.get(d), startDate, endDate)) {
                doctorDTOS.add(DoctorDTO.builder()
                          .id(d.getId())
                          .firstName(d.getFirstName())
                          .lastName(d.getLastName())
                          .email(d.getEmail())
                          .city(d.getCity())
                          .country(d.getCountry())
                          .clinic(ClinicDTO.builder().name(d.getClinic().getName()).build())
                          .build());
            }
        }

        return new ResponseEntity<>(doctorDTOS, HttpStatus.OK);
    }
}
