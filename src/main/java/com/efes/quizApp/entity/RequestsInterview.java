package com.efes.quizApp.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests_interview")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@IdClass(RequestsInterviewId.class)
public class RequestsInterview extends BaseEntity{

    @Id
    @Column(name = "devId", nullable = false)
    private Long devId;

    @Id
    @Column(name = "companyId", nullable = false)
    private Long companyId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "duration")
    private int duration;

    @Column(name = "startDate")
    private Date startDate;

}
