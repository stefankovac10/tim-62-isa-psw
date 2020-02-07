package com.ProjectCC.dero.service;


import com.ProjectCC.dero.dto.ClinicCenterAdministratorDTO;
import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.model.ClinicCenterAdministrator;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.ClinicCenterAdministratorRepository;
import com.ProjectCC.dero.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicCenterAdministratorService {

    private ClinicCenterAdministratorRepository clinicCenterAdministratorRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;

    @Autowired
    public ClinicCenterAdministratorService(ClinicCenterAdministratorRepository clinicCenterAdministratorRepository, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.clinicCenterAdministratorRepository = clinicCenterAdministratorRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    public ClinicCenterAdministratorDTO save(ClinicCenterAdministratorDTO ccadmindto) {
        ClinicCenterAdministrator ccadmin = modelMapper.map(ccadmindto, ClinicCenterAdministrator.class);
        ccadmin.setLogFirstTime(false);
        ccadmin.setPassword(passwordEncoder.encode(ccadmin.getPassword()));
        ccadmin.setEnabled(true);
        List<Authority> authorities = authorityService.findByName("ROLE_CCADMIN");
        ccadmin.setAuthorities(authorities);

        User user = userRepository.findByEmail(ccadmin.getEmail());
        User user2 = userRepository.findByJmbg(ccadmin.getJmbg());
        User user3 = userRepository.findByTelephone(ccadmin.getTelephone());

        if (user == null && user2 == null && user3 == null) {
            ccadmin = clinicCenterAdministratorRepository.save(ccadmin);
            return modelMapper.map(ccadmin, ClinicCenterAdministratorDTO.class);
        }
        return null;
    }


    public ClinicCenterAdministrator findOne(Long id) {
        return clinicCenterAdministratorRepository.findById(id).orElseGet(null);
    }

}
