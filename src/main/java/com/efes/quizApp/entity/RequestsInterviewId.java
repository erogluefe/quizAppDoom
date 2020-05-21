package com.efes.quizApp.entity;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RequestsInterviewId implements Serializable {

    private Long devId;

    private Long companyId;
}
