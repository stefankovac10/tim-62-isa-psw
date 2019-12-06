package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DoctorDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.repository.DoctorRepository;
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

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, ClinicService clinicService) {
        this.doctorRepository = doctorRepository;
        this.clinicService = clinicService;
    }

    public ResponseEntity<DoctorDTO> save(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor(
                doctorDTO.getFirstName(),
                doctorDTO.getLastName(),
                doctorDTO.getJmbg(),
                doctorDTO.getPassword(),
                doctorDTO.getEmail(),
                doctorDTO.getAddress(),
                doctorDTO.getCity(),
                doctorDTO.getCountry(),
                doctorDTO.getTelephone());

        Clinic clinic = clinicService.findOne((long) 1);
        doctor.setClinic(clinic);

        doctor = doctorRepository.save(doctor);

        return new ResponseEntity<>(new DoctorDTO(doctor), HttpStatus.CREATED);
    }

    public ResponseEntity<Void> delete(Long id) {
        doctorRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<DoctorDTO>> findAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorsDTO = new ArrayList<>();

        for (Doctor d : doctors) {
            doctorsDTO.add(new DoctorDTO(d));
        }

        return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<DoctorDTO>> pronadjiPoImenuMejluGraduDrzavi(String firstName, String lastName, String email, String city, String country){
        List<Doctor> doctors = doctorRepository.pronadjiDoktorePoImenuMejluGraduDrzavi(firstName, lastName, email, city, country);
        List<DoctorDTO> doctorsDTO = new ArrayList<>();

        for (Doctor d : doctors) {
            doctorsDTO.add(new DoctorDTO(d));
        }

        return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
    }
}
