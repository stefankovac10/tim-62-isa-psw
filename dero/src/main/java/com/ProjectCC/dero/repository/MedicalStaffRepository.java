package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long> {
    MedicalStaff findByEmail(String email);
}
