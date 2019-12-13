package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.repository.RegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationRequestService {

    private RegistrationRequestRepository registrationRequestRepository;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;

    @Autowired
    public RegistrationRequestService(RegistrationRequestRepository registrationRequestRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.registrationRequestRepository = registrationRequestRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    public RegistrationRequest save(RegistrationRequest registrationRequest){
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        List<Authority> authorities = authorityService.findByName("ROLE_REQUEST");
        registrationRequest.setAuthorities(authorities);
        registrationRequest.setEnabled(false);
        return registrationRequestRepository.save(registrationRequest);
    }

    public List<RegistrationRequest> findAll() {
        return registrationRequestRepository.findAll();
    }

    public RegistrationRequest findById(Long id) {
        return registrationRequestRepository.findById(id).orElseGet(null);
    }

    public void remove(Long id) {
        registrationRequestRepository.deleteById(id);
    }

    public RegistrationRequest findOne(Long id) {
        return registrationRequestRepository.findById(id).orElseGet(null);
    }

    public void update(RegistrationRequest registrationRequest) {
        registrationRequestRepository.update(registrationRequest.getId(), registrationRequest.isVerified());
    }
}
