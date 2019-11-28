package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);

}
