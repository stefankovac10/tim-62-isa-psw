package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByJmbg(String jmbg);

    User findByTelephone(String telephone);

    User findByEmail(String email);
}
