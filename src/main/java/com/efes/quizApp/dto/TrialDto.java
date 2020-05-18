package com.efes.quizApp.dto;

import com.efes.quizApp.entity.TrialId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrialDto {

    private Long devId;

    private Long quizId;

    private int trial_no;

    private double success_rate;

    private int choosen_options;
}
