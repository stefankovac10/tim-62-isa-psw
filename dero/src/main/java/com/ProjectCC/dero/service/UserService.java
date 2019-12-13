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

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<UserDTO> findById(Long id) {
        Optional<User> opt = this.userRepository.findById(id);
        User user = opt.get();
        return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.FOUND);
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
}
