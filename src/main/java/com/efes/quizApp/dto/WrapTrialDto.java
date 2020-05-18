package com.efes.quizApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WrapTrialDto {
    List<TrialDto> trialDtoList;
}
