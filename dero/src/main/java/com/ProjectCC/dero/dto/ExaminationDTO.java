package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Examination;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExaminationDTO {
    private String report;
    private String medicine;
    private String diagnosis;

    public ExaminationDTO() {

    }

    public ExaminationDTO(Examination examination) {

    }

    public ExaminationDTO(String report, String medicine, String diagnosis) {
        this.report = report;
        this.medicine = medicine;
        this.diagnosis = diagnosis;
    }
}
