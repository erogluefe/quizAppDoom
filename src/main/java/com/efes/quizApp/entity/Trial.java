package com.efes.quizApp.entity;

import lombok.*;

import javax.persistence.*;


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
    @Column(name = "devId", nullable = false)
    private long devId;

    @Id
    @Column(name = "quizId", nullable = false)
    private long quizId;

    @Id
    @Column(name = "trial_no", nullable = false)
    private int trialNo;

    @Column(name = "successRate")
    private double successRate;

    @Column( name= "choosenOptions")
    private int choosenOptions;
}
