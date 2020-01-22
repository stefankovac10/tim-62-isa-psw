package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.MedicalStaffDTO;
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
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public VacationRequestService(VacationRequestRepository vacationRequestRepository, ClinicRepository clinicRepository,
                                  UserRepository userRepository, ModelMapper modelMapper) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.clinicRepository = clinicRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public VacationRequestDTO add(VacationRequestDTO vac) {
        Optional<Clinic> opt = this.clinicRepository.findById((long)1);
        Optional<User> med = this.userRepository.findById(vac.getMedicalStaff().getId());

        if (opt.isPresent() && med.isPresent()) {
            Clinic clinic = opt.get();
            User user = med.get();
            VacationRequest vacationRequest = modelMapper.map(vac, VacationRequest.class);
            vacationRequest.setClinic(clinic);
            vacationRequest.setMedicalStaff((MedicalStaff) user);
            vacationRequest = this.vacationRequestRepository.save(vacationRequest);
            return VacationRequestDTO.builder()
                    .id(vacationRequest.getId())
                    .startDate(vacationRequest.getStartDate())
                    .endDate(vacationRequest.getEndDate()).build();
        } else return null;



    }

    public List<VacationRequestDTO> getAll() {
        ArrayList<VacationRequest> vacs = (ArrayList<VacationRequest>) this.vacationRequestRepository.findAll();
        List<VacationRequestDTO> retVal = new ArrayList<>();
        for (VacationRequest vac : vacs) {
            MedicalStaffDTO medicalStaffDTO = MedicalStaffDTO.builder()
                    .firstName(vac.getMedicalStaff().getFirstName())
                    .lastName(vac.getMedicalStaff().getLastName())
                    .build();
            retVal.add(VacationRequestDTO.builder()
                    .id(vac.getId())
                    .startDate(vac.getStartDate())
                    .endDate(vac.getEndDate())
                    .medicalStaff(medicalStaffDTO)
                    .accepted(vac.isAccepted()).build());
        }
        return retVal;
    }
}
