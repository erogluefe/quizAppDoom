package com.efes.quizApp.entity;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TrialId implements Serializable {

    private long devId;

    private long quizId;

    private int trialNo;

}
