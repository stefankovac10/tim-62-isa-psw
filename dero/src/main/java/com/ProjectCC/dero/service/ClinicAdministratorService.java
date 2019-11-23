package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.ClinicAdministrator;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.ClinicAdministratorRepository;
import com.ProjectCC.dero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicAdministratorService {
    private ClinicAdministratorRepository clinicAdministratorRepository;
    private UserRepository userRepository;

    @Autowired
    public ClinicAdministratorService(ClinicAdministratorRepository clinicAdministratorRepository, UserRepository userRepository) {
        this.clinicAdministratorRepository = clinicAdministratorRepository;
        this.userRepository = userRepository;
    }

    public ClinicAdministrator save(ClinicAdministrator clinicAdministrator) {

        User user = userRepository.findByEmail(clinicAdministrator.getEmail());

        if(user == null){
            return clinicAdministratorRepository.save(clinicAdministrator);
        }

        return null;

    }
}
