package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.User;
import com.ProjectCC.dero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> saveOrUpdate() {
        return userRepository.findAll();
    }
}
