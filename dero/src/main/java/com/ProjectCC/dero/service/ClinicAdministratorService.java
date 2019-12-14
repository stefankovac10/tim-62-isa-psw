package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.model.ClinicAdministrator;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.ClinicAdministratorRepository;
import com.ProjectCC.dero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicAdministratorService {
    private ClinicAdministratorRepository clinicAdministratorRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;

    @Autowired
    public ClinicAdministratorService(ClinicAdministratorRepository clinicAdministratorRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.clinicAdministratorRepository = clinicAdministratorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    public ClinicAdministrator save(ClinicAdministrator clinicAdministrator) {

        User user = userRepository.findByEmail(clinicAdministrator.getEmail());

        clinicAdministrator.setPassword(passwordEncoder.encode(clinicAdministrator.getPassword()));
        List<Authority> authorities = authorityService.findByName("ROLE_CADMIN");
        clinicAdministrator.setAuthorities(authorities);

        if(user == null){
            return clinicAdministratorRepository.save(clinicAdministrator);
        }

        return null;

    }
}
