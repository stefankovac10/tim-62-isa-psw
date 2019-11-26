package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Query("update MedicalRecord m set m.height=?1 , m.weight =?2 , m.bloodType =?3, m.diopter =?4  where m.id=?5")
    void update(int height, int weight, String bloodType, String diopter, Long id);
}
