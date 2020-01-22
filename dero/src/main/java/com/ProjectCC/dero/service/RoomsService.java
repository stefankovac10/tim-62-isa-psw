package com.ProjectCC.dero.service;

import com.ProjectCC.dero.controller.RoomsController;
import com.ProjectCC.dero.dto.RoomDTO;
import com.ProjectCC.dero.model.Room;
import com.ProjectCC.dero.repository.RoomsRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomsService {

    private RoomsRepository roomsRepository;

    @Autowired
    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    public ResponseEntity<List<RoomDTO>> search(String name, int number, DateTime date, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        String sName = "%" + name + "%";
        Page<Room> rooms = this.roomsRepository.search(sName, number, date, pageable);

        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : rooms.getContent()) {
            roomDTOS.add(RoomDTO.builder()
                .name(r.getName())
                .number(r.getNumber())
                .id(r.getId()).build());
        }
        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }

    public List<RoomDTO> getAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = this.roomsRepository.findAll(pageable);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : roomPage.getContent()) {
            roomDTOS.add(RoomDTO.builder()
                .name(r.getName())
                .number(r.getNumber())
                .id(r.getId()).build());
        }
        return roomDTOS;
    }
}
