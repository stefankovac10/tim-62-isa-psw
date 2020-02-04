package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Clinic;
import com.ProjectCC.dero.util.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.joda.time.DateTime;

@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private int number;
    private String name;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime nextAvailable;
    public ClinicDTO clinic;
    public String type;
}
