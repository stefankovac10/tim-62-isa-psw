package com.ProjectCC.dero.service;

import com.ProjectCC.dero.dto.UserDTO;
import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;


    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public ResponseEntity<UserDTO> findByEmail(String email) {
        Optional<User> opt = Optional.ofNullable(this.userRepository.findByEmail(email));
        return getUserDTOResponseEntity(opt);
    }

    public ResponseEntity<UserDTO> findById(Long id) {
        Optional<User> opt = this.userRepository.findById(id);
        return getUserDTOResponseEntity(opt);
    }

    private ResponseEntity<UserDTO> getUserDTOResponseEntity(Optional<User> opt) {
        UserDTO userDTO = null;
        if (opt.isPresent()) {
            User user = opt.get();
            userDTO = UserDTO.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .jmbg(user.getJmbg())
                    .telephone(user.getTelephone())
                    .country(user.getCountry())
                    .city(user.getCity())
                    .address(user.getAddress())
                    .build();
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> edit(UserDTO userDTO) {
        Optional<User> opt = this.userRepository.findById(userDTO.getId());
        User user = opt.get();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setJmbg(userDTO.getJmbg());
        user.setTelephone(userDTO.getTelephone());
        this.userRepository.save(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteById(Long id) {
        this.userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
