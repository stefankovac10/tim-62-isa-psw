package com.ProjectCC.dero.service;

import com.ProjectCC.dero.model.Authority;
import com.ProjectCC.dero.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public List<Authority> findById(Long id){
        Authority authority = this.authorityRepository.getOne(id);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);

        return authorities;
    }

    public List<Authority> findByName(String name){
        Authority authority = this.authorityRepository.findByName(name);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);

        return authorities;
    }

}
