package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Operation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class OperationRoomDTO {
    @Setter
    private Long id;
    private int number;
    private String name;

    @Autowired
    public OperationRoomDTO() {
    }

    public OperationRoomDTO(Long id, int number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }
}
