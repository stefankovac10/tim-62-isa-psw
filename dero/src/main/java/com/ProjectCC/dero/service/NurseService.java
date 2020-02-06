package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.NurseDTO;
import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Examination;
import com.ProjectCC.dero.model.Nurse;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.DoctorRepository;
import com.ProjectCC.dero.repository.ExaminationRepository;
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
    private ExaminationRepository examinationRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;

    @Autowired
    public NurseService(ModelMapper modelMapper, NurseRepository nurseRepository, ExaminationRepository examinationRepository,
                        ClinicRepository clinicRepository, PasswordEncoder passwordEncoder,
                        AuthorityService authorityService) {
        this.nurseRepository = nurseRepository;
        this.clinicRepository = clinicRepository;
        this.examinationRepository = examinationRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    public NurseDTO add(NurseDTO nurseDTO) {
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

    public ResponseEntity<Void> deleteNurse(Long id) {
        Optional<Nurse> optionalNurse = this.nurseRepository.findById(id);
        if (!optionalNurse.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Nurse nurse = optionalNurse.get();
        List<Examination> examinations = this.examinationRepository.findByNurse(nurse);

        for (Examination examination : examinations) {
            examination.setNurse(null);
            this.examinationRepository.save(examination);
        }

        this.nurseRepository.delete(nurse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
