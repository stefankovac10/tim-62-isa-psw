package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.RegistrationRequest;
import com.ProjectCC.dero.repository.RegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationRequestService {
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    public RegistrationRequestService(RegistrationRequestRepository registrationRequestRepository) {
        this.registrationRequestRepository = registrationRequestRepository;
    }

    public RegistrationRequest save(RegistrationRequest registrationRequest){
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
