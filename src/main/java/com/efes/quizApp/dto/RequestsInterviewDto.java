package com.efes.quizApp.dto;

import com.efes.quizApp.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestsInterviewDto {
    private Long devId;
    private Long companyId;
    private Status status;
    private int duration;
    private Date startDate;
}
