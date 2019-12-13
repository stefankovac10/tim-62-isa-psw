package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private ClinicService clinicService;
    private ModelMapper modelMapper;

    @Autowired
    public DoctorService(ModelMapper modelMapper, DoctorRepository doctorRepository, ClinicService clinicService) {
        this.doctorRepository = doctorRepository;
        this.clinicService = clinicService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<DoctorDTO> save(DoctorDTO doctorDTO) {
        Doctor doctor = Doctor.builder()
                        .firstName(doctorDTO.getFirstName())
                        .lastName(doctorDTO.getLastName())
                        .jmbg(doctorDTO.getJmbg())
                        .password(doctorDTO.getPassword())
                        .address(doctorDTO.getAddress())
                        .city(doctorDTO.getCity())
                        .country(doctorDTO.getCountry())
                        .telephone(doctorDTO.getTelephone())
                        .email(doctorDTO.getEmail())
                        .grade((double)0)
                        .build();

        Clinic clinic = clinicService.findOne((long) 1);
        doctor.setClinic(clinic);

        doctor = doctorRepository.save(doctor);

        return new ResponseEntity<>(doctorDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> delete(Long id) {
        doctorRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<DoctorDTO>> findAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorsDTO = new ArrayList<>();

        for (Doctor d : doctors) {
            doctorsDTO.add(modelMapper.map(d, DoctorDTO.class));
        }

        return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<DoctorDTO>> pronadjiPoImenuMejluGraduDrzavi(String firstName, String lastName, String email, String city, String country){
        List<Doctor> doctors = doctorRepository.pronadjiDoktorePoImenuMejluGraduDrzavi(firstName, lastName, email, city, country);
        List<DoctorDTO> doctorsDTO = new ArrayList<>();

        for (Doctor d : doctors) {
            doctorsDTO.add(modelMapper.map(d, DoctorDTO.class));
        }

        return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
    }
}
