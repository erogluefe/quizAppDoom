package com.efes.quizApp.service;

import com.efes.quizApp.dto.TrialDto;
import com.efes.quizApp.dto.WrapTrialDto;

public interface TrialService {

    TrialDto save(TrialDto trialInfo);

    Boolean delete(TrialDto trialDto);

    TrialDto getById(Long devId, Long quizId, int noTrials);

    WrapTrialDto getTrials(Long quizId, Long devId);

    WrapTrialDto getAllDevTrials(Long devId);

    WrapTrialDto getQuizTrials(Long quizId);

    // need functionality to delete trials
}
