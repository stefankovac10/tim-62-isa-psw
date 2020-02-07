package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.ClinicAdministratorDTO;
import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.ClinicAdministrator;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.ClinicAdministratorRepository;
import com.ProjectCC.dero.repository.ClinicRepository;
import com.ProjectCC.dero.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicAdministratorService {
    private ClinicAdministratorRepository clinicAdministratorRepository;
    private UserRepository userRepository;
    private ClinicRepository clinicRepository;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;
    private ModelMapper modelMapper;

    @Autowired
    public ClinicAdministratorService(ClinicAdministratorRepository clinicAdministratorRepository, UserRepository userRepository, ClinicRepository clinicRepository,
                                      PasswordEncoder passwordEncoder, AuthorityService authorityService, ModelMapper modelMapper) {
        this.clinicAdministratorRepository = clinicAdministratorRepository;
        this.userRepository = userRepository;
        this.clinicRepository = clinicRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.modelMapper = modelMapper;
    }

    public ClinicAdministratorDTO save(ClinicAdministratorDTO clinicAdministratorDTO) {

        ClinicAdministrator cadmin = modelMapper.map(clinicAdministratorDTO, ClinicAdministrator.class);

        Clinic clinic = this.clinicRepository.findByName(clinicAdministratorDTO.getClinic().getName());
        cadmin.setClinic(clinic);

        clinicAdministratorDTO.setId(cadmin.getId());
        User user = userRepository.findByEmail(cadmin.getEmail());
        User user2 = userRepository.findByJmbg(cadmin.getJmbg());
        User user3 = userRepository.findByTelephone(cadmin.getTelephone());

        cadmin.setPassword(passwordEncoder.encode(cadmin.getPassword()));
        List<Authority> authorities = authorityService.findByName("ROLE_CADMIN");
        cadmin.setAuthorities(authorities);
        cadmin.setEnabled(true);

        if(user == null && user2 == null && user3 == null){
            cadmin = clinicAdministratorRepository.save(cadmin);
            clinicAdministratorDTO.setId(cadmin.getId());
            return clinicAdministratorDTO;
        }

        return null;

    }

    public Long update(ClinicAdministratorDTO cadminDTO) {
        ClinicAdministrator cadmin = new ClinicAdministrator();
        cadmin.setFirstName(cadminDTO.getFirstName());
        cadmin.setLastName(cadminDTO.getLastName());
        cadmin.setJmbg(cadminDTO.getJmbg());
        cadmin.setAddress(cadminDTO.getAddress());
        cadmin.setCity(cadminDTO.getCity());
        cadmin.setCountry(cadminDTO.getCountry());
        cadmin.setTelephone(cadminDTO.getTelephone());
        cadmin.setEmail(cadminDTO.getEmail());
        cadmin.setPassword(cadminDTO.getPassword());

        cadmin = this.clinicAdministratorRepository.save(cadmin);
        return cadmin.getId();
    }
}
