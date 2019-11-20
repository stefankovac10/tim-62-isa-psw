package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.Doctor;
import com.ProjectCC.dero.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;

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
}
