package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public List<Doctor> pronadjiPoImenuMejluGraduDrzavi(String firstName,
                                                        String lastName,
                                                        String email,
                                                        String city,
                                                        String country){
        return doctorRepository.pronadjiDoktorePoImenuMejluGraduDrzavi(firstName,
                                                                        lastName,
                                                                        email,
                                                                        city,
                                                                        country);
    }
}
