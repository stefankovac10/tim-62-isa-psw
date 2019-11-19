package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.dto.TypeOfExaminationDTO;
import com.ProjectCC.dero.model.TypeOfExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TypeOfExaminationRepository extends JpaRepository<TypeOfExamination, Long> {

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query("update TypeOfExamination t set t.name=?1, t.description =?2  where t.id=?3")
    void update(String name, String description, Long id);
}
