package com.ProjectCC.dero.repository;

import com.ProjectCC.dero.model.Room;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomsRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where lower(r.name) like lower(?1) and r.number = ?2")
    Page<Room> search(String sName, int number, Pageable pageable);

    @Query("select r from Room r where lower(r.name) like lower(?1)")
    Page<Room> searchName(String sName, Pageable pageable);
}
