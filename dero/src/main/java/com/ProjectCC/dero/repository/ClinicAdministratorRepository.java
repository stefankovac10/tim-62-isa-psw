package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.ClinicAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicAdministratorRepository extends JpaRepository<ClinicAdministrator, Long> {
    ClinicAdministrator findByEmail(String email);
}
