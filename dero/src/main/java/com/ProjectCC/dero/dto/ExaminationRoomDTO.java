package com.ProjectCC.dero.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class ExaminationRoomDTO {
    @Setter
    private Long id;
    private int number;
    private String name;

    @Autowired
    public ExaminationRoomDTO() {
    }

    public ExaminationRoomDTO(Long id, int number, String name) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
}
