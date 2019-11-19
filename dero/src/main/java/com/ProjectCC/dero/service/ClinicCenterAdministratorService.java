package com.ProjectCC.dero.service;


import com.ProjectCC.dero.model.ClinicCenterAdministrator;
import com.ProjectCC.dero.repository.ClinicCenterAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicCenterAdministratorService {

    private ClinicCenterAdministratorRepository clinicCenterAdministratorRepository;

    @Autowired
    public ClinicCenterAdministratorService(ClinicCenterAdministratorRepository clinicCenterAdministratorRepository) {
        this.clinicCenterAdministratorRepository = clinicCenterAdministratorRepository;
    }

    public ClinicCenterAdministrator save(ClinicCenterAdministrator clinicCenterAdministrator){
        return clinicCenterAdministratorRepository.save(clinicCenterAdministrator);
    }

    public ClinicCenterAdministrator findOne(Long id) {
        return clinicCenterAdministratorRepository.findById(id).orElseGet(null);
    }

}
