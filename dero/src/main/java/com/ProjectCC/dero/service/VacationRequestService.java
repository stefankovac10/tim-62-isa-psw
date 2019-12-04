package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicAdministratorDTO;
import com.ProjectCC.dero.dto.VacationRequestDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VacationRequestService {
    private VacationRequestRepository vacationRequestRepository;
    private ClinicRepository clinicRepository;
    private ClinicAdministratorRepository clinicAdministratorRepository;

    @Autowired
    public VacationRequestService(VacationRequestRepository vacationRequestRepository, ClinicRepository clinicRepository,
                                  ClinicAdministratorRepository clinicAdministratorRepository) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.clinicRepository = clinicRepository;
        this.clinicAdministratorRepository = clinicAdministratorRepository;
    }


    public VacationRequestDTO add(VacationRequestDTO vac) {
        Optional<Clinic> opt = this.clinicRepository.findById((long)1);
        Clinic clinic = opt.get();
        Optional<ClinicAdministrator> opti = this.clinicAdministratorRepository.findById((long)3);
        ClinicAdministrator admin = opti.get();
        VacationRequest vacationRequest = VacationRequest.builder()
                .startDate(vac.getStartDate())
                .endDate(vac.getEndDate())
                .clinic(clinic)
                .administrator(admin)
                .build();

        vacationRequest = this.vacationRequestRepository.save(vacationRequest);
        vac.setId(vacationRequest.getId());
        return vac;
    }

    public List<VacationRequestDTO> getAll() {
        ArrayList<VacationRequest> vacs = (ArrayList<VacationRequest>) this.vacationRequestRepository.findAll();
        List<VacationRequestDTO> retVal = new ArrayList<>();
        for (VacationRequest vac : vacs) {
            retVal.add(VacationRequestDTO.builder()
            .startDate(vac.getStartDate())
            .endDate(vac.getEndDate())
            .accepted(vac.isAccepted())
            .build());
//            retVal.add(new VacationRequestDTO(vac.getStartDate(), vac.getEndDate()));
        }
        return retVal;
    }
}
