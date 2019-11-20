package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.ClinicAdministrator;
import com.ProjectCC.dero.repository.ClinicAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicAdministratorService {
    private ClinicAdministratorRepository clinicAdministratorRepository;

    @Autowired
    public ClinicAdministratorService(ClinicAdministratorRepository clinicAdministratorRepository) {
        this.clinicAdministratorRepository = clinicAdministratorRepository;
    }

    public ClinicAdministrator save(ClinicAdministrator clinicAdministrator) {
        return clinicAdministratorRepository.save(clinicAdministrator);
    }
}
