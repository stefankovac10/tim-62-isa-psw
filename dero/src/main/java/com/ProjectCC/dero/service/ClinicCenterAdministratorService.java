package com.ProjectCC.dero.service;


import com.ProjectCC.dero.dto.ClinicCenterAdministratorDTO;
import com.ProjectCC.dero.model.ClinicCenterAdministrator;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.ClinicCenterAdministratorRepository;
import com.ProjectCC.dero.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicCenterAdministratorService {

    private ClinicCenterAdministratorRepository clinicCenterAdministratorRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ClinicCenterAdministratorService(ModelMapper modelMapper, ClinicCenterAdministratorRepository clinicCenterAdministratorRepository, UserRepository userRepository){
        this.clinicCenterAdministratorRepository = clinicCenterAdministratorRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public ClinicCenterAdministratorDTO save(ClinicCenterAdministratorDTO ccadmindto) {
        ClinicCenterAdministrator ccadmin = modelMapper.map(ccadmindto, ClinicCenterAdministrator.class);
        ccadmin.setLogFirstTime(false);
        User user = userRepository.findByEmail(ccadmin.getEmail());
        User user2 = userRepository.findByJmbg(ccadmin.getJmbg());
        User user3 = userRepository.findByTelephone(ccadmin.getTelephone());

        if (user == null && user2 == null && user3 == null) {
            ccadmin = clinicCenterAdministratorRepository.save(ccadmin);
            return modelMapper.map(ccadmin, ClinicCenterAdministratorDTO.class);
        }
        return null;
    }


    public ClinicCenterAdministrator findOne(Long id) {
        return clinicCenterAdministratorRepository.findById(id).orElseGet(null);
    }

}
