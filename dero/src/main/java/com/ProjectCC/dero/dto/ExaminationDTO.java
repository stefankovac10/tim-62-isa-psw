package com.ProjectCC.dero.dto;

import com.ProjectCC.dero.model.Diagnosis;
import com.ProjectCC.dero.model.Examination;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExaminationDTO {
    private Long id;
    private String report;
    private String medicine;
    private String diagnosis;

    public ExaminationDTO() {

    }

    public ExaminationDTO(Examination examination) {
        this.id = examination.getId();
        this.diagnosis = null;
        this.medicine = null;
        this.report = examination.getReport();
    }

    public ExaminationDTO(String report, String medicine, String diagnosis, Long id) {
        this.id = id;
        this.report = report;
        this.medicine = medicine;
        this.diagnosis = diagnosis;
    }
}
