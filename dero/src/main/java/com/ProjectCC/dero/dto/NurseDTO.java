package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Nurse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NurseDTO {
    private Long id;


    public NurseDTO(Nurse nurse) {
        this.id = nurse.getId();
    }

    public NurseDTO() {
    }

    public NurseDTO(Long id) {
        this.id = id;
    }
}
