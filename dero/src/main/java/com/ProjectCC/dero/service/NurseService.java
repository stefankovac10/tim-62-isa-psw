package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.NurseDTO;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Nurse;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.repository.NurseRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NurseService {
    private NurseRepository nurseRepository;
    private ClinicRepository clinicRepository;
    private ModelMapper modelMapper;

    @Autowired
    public NurseService(ModelMapper modelMapper, NurseRepository nurseRepository, ClinicRepository clinicRepository) {
        this.nurseRepository = nurseRepository;
        this.clinicRepository = clinicRepository;
        this.modelMapper = modelMapper;
    }

    public NurseDTO add(NurseDTO nurseDTO) {
        Nurse nurse = Nurse.builder()
                .firstName(nurseDTO.getFirstName())
                .lastName(nurseDTO.getLastName())
                .jmbg(nurseDTO.getJmbg())
                .password(nurseDTO.getPassword())
                .address(nurseDTO.getAddress())
                .city(nurseDTO.getCity())
                .country(nurseDTO.getCountry())
                .telephone(nurseDTO.getTelephone())
                .email(nurseDTO.getEmail())
                .build();

        Optional<Clinic> clinicOpt = this.clinicRepository.findById((long) 1);
        Clinic clinic = clinicOpt.get();
        nurse.setClinic(clinic);
        nurse = this.nurseRepository.save(nurse);
        
        nurseDTO.setId(nurse.getId());

        return nurseDTO;
    }
}
