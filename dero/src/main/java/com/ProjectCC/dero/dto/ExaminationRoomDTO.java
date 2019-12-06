package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationRoomDTO extends RoomDTO {
    @Setter
    private Long id;
    private int number;
    private String name;
    @Setter
    private ClinicDTO clinic;

//    @Autowired
//    public ExaminationRoomDTO() {
//    }
//
//    public ExaminationRoomDTO(Long id, int number, String name) {
//        this.id = id;
//        this.name = name;
//        this.number = number;
//    }
}
