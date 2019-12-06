package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.DoctorDTO;
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

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor save(Doctor doc) {
        return doctorRepository.save(doc);
    }

    public void delete(Long id) {
//        Long idLong = (Long)id;
        doctorRepository.deleteById(id);
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
