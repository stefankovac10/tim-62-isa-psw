package com.ProjectCC.dero.service;


import com.ProjectCC.dero.model.ClinicCenterAdministrator;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.ClinicCenterAdministratorRepository;
import com.ProjectCC.dero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicCenterAdministratorService {

    private ClinicCenterAdministratorRepository clinicCenterAdministratorRepository;
    private UserRepository userRepository;

    @Autowired
    public ClinicCenterAdministratorService(ClinicCenterAdministratorRepository clinicCenterAdministratorRepository, UserRepository userRepository) {
        this.clinicCenterAdministratorRepository = clinicCenterAdministratorRepository;
        this.userRepository = userRepository;
    }


    public ClinicCenterAdministrator save(ClinicCenterAdministrator clinicCenterAdministrator) {
        User user = userRepository.findByEmail(clinicCenterAdministrator.getEmail());
        User user2 = userRepository.findByJmbg(clinicCenterAdministrator.getJmbg());
        User user3 = userRepository.findByTelephone(clinicCenterAdministrator.getTelephone());

        if (user == null && user2 == null && user3 == null) {
            return clinicCenterAdministratorRepository.save(clinicCenterAdministrator);
        }
        return null;
    }


    public ClinicCenterAdministrator findOne(Long id) {
        return clinicCenterAdministratorRepository.findById(id).orElseGet(null);
    }

}
