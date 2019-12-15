package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.NurseDTO;
import com.ProjectCC.dero.model.Authority;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NurseService {
    private NurseRepository nurseRepository;
    private ClinicRepository clinicRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;

    @Autowired
    public NurseService(ModelMapper modelMapper, NurseRepository nurseRepository,
                        ClinicRepository clinicRepository, PasswordEncoder passwordEncoder,
                        AuthorityService authorityService) {
        this.nurseRepository = nurseRepository;
        this.clinicRepository = clinicRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    public NurseDTO add(NurseDTO nurseDTO) {
//        Nurse nurse = Nurse.builder()
//                .firstName(nurseDTO.getFirstName())
//                .lastName(nurseDTO.getLastName())
//                .jmbg(nurseDTO.getJmbg())
//                .password(nurseDTO.getPassword())
//                .address(nurseDTO.getAddress())
//                .city(nurseDTO.getCity())
//                .country(nurseDTO.getCountry())
//                .telephone(nurseDTO.getTelephone())
//                .email(nurseDTO.getEmail())
//                .build();
        Nurse nurse = modelMapper.map(nurseDTO, Nurse.class);
        nurse.setPassword(passwordEncoder.encode(nurseDTO.getPassword()));
        List<Authority> authorities = authorityService.findByName("ROLE_NURSE");
        nurse.setAuthorities(authorities);

        Optional<Clinic> clinicOpt = this.clinicRepository.findById((long) 1);
        Clinic clinic = clinicOpt.get();
        nurse.setClinic(clinic);
        nurse = this.nurseRepository.save(nurse);
        
        nurseDTO.setId(nurse.getId());

        return nurseDTO;
    }
}
