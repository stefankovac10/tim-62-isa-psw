package com.ProjectCC.dero.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomExaminationRequestDTO {
    private String date;
    private Long doctorID;
    private int duration;
    private String email;
    private String time;
}
