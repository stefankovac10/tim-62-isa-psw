package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Operation;
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
public class OperationRoomDTO extends RoomDTO {
    @Setter
    private Long id;
    @Setter
    private ClinicDTO clinic;

}
