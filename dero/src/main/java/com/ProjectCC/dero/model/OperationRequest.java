package com.ProjectCC.dero.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long doctorId;

    @Column
    private Long patientId;

    @Column(name = "date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    private DateTime date;

    @Column(name = "duration", nullable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDurationAsMillisLong")
    private Duration duration;

}
