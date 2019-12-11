package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private int number;
    private String name;
    public ClinicDTO clinic;
}
