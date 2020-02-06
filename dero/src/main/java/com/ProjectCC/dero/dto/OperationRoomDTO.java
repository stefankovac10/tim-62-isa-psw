package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OperationRoomDTO extends RoomDTO {
    private Long id;
    private Long requestId;

//    @Autowired
//    public OperationRoomDTO() {
//    }
//
//    public OperationRoomDTO(Long id, int number, String name) {
//        this.id = id;
//        this.number = number;
//        this.name = name;
//    }
}
