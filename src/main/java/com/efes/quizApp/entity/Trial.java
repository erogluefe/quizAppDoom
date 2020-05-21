package com.efes.quizApp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "trials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@IdClass(TrialId.class)
public class Trial extends BaseEntity{

    @Id
//    @ManyToOne
    @Column(name = "devId", nullable = false)
    private long devId;

    @Id
//    @ManyToOne
    @Column(name = "quizId", nullable = false)
    private long quizId;

    @Id
    @Column(name = "trialNo", nullable = false)
    private int trialNo;

    @Column(name = "successRate")
    private double successRate;

    @Column( name= "choosenOptions")
    private String choosenOptions;

    @Column( name = "passed")
    private boolean passed;
}
