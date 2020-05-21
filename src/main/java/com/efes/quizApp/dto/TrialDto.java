package com.efes.quizApp.dto;

import com.efes.quizApp.entity.TrialId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrialDto {

    private Long devId;

    private Long quizId;

    private int trialNo;

    private double successRate;

    private List<String> choosenOptions;

    private boolean passed;
}
