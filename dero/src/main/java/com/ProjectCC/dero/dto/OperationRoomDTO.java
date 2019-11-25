package com.ProjectCC.dero.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class OperationRoomDTO {
    private Long id;
    private int number;
    private String name;

    @Autowired
    public OperationRoomDTO(int number, String name) {
        this.number = number;
        this.name = name;
    }
}
