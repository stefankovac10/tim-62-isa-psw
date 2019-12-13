package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.dto.TypeOfExaminationDTO;
import com.ProjectCC.dero.model.TypeOfExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TypeOfExaminationRepository extends JpaRepository<TypeOfExamination, Long> {

    TypeOfExamination findByName(String type);
}
