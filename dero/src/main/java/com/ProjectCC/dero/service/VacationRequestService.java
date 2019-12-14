package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicAdministratorDTO;
import com.ProjectCC.dero.dto.VacationRequestDTO;
import com.ProjectCC.dero.model.*;
import com.ProjectCC.dero.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VacationRequestService {
    private VacationRequestRepository vacationRequestRepository;
    private ClinicRepository clinicRepository;
    private ModelMapper modelMapper;

    @Autowired
    public VacationRequestService(VacationRequestRepository vacationRequestRepository, ClinicRepository clinicRepository,
                                  ModelMapper modelMapper) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.clinicRepository = clinicRepository;
        this.modelMapper = modelMapper;
    }


    public VacationRequestDTO add(VacationRequestDTO vac) {
        Optional<Clinic> opt = this.clinicRepository.findById((long)1);
        Clinic clinic = opt.get();
        VacationRequest vacationRequest = modelMapper.map(vac, VacationRequest.class);
        vacationRequest.setClinic(clinic);
        vacationRequest = this.vacationRequestRepository.save(vacationRequest);
        return VacationRequestDTO.builder()
                .id(vacationRequest.getId())
                .startDate(vacationRequest.getStartDate())
                .endDate(vacationRequest.getEndDate()).build();
    }

    public List<VacationRequestDTO> getAll() {
        ArrayList<VacationRequest> vacs = (ArrayList<VacationRequest>) this.vacationRequestRepository.findAll();
        List<VacationRequestDTO> retVal = new ArrayList<>();
        for (VacationRequest vac : vacs) {
            retVal.add(VacationRequestDTO.builder()
                    .id(vac.getId())
                    .startDate(vac.getStartDate())
                    .endDate(vac.getEndDate())
                    .accepted(vac.isAccepted()).build());
        }
        return retVal;
    }
}
