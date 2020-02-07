package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.RegistrationRequestDTO;
import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.repository.RegistrationRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationRequestService {

    private RegistrationRequestRepository registrationRequestRepository;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;
    private ModelMapper modelMapper;

    @Autowired
    public RegistrationRequestService(RegistrationRequestRepository registrationRequestRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.registrationRequestRepository = registrationRequestRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.modelMapper = modelMapper;
    }

    public RegistrationRequest save(RegistrationRequest registrationRequest){
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        List<Authority> authorities = authorityService.findByName("ROLE_REQUEST");
        registrationRequest.setAuthorities(authorities);
        registrationRequest.setEnabled(false);
        return registrationRequestRepository.save(registrationRequest);
    }

    public List<RegistrationRequestDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC,"firstName"));
        Page<RegistrationRequest> registrationRequests = registrationRequestRepository.findAll(pageable);

        List<RegistrationRequestDTO> registrationRequestDTOS = new ArrayList<>();
        for(RegistrationRequest r : registrationRequests){
            registrationRequestDTOS.add(RegistrationRequestDTO.builder()
                                        .firstName(r.getFirstName())
                                        .lastName(r.getLastName())
                                        .telephone(r.getTelephone())
                                        .address(r.getAddress())
                                        .city(r.getCity())
                                        .country(r.getCountry())
                                        .email(r.getEmail())
                                        .id(r.getId())
                                        .jmbg(r.getJmbg())
                                        .password(r.getPassword())
                                        .verified(r.isVerified())
                                        .pages(registrationRequests.getTotalPages())
                                        .build());
        }

        return registrationRequestDTOS;
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
