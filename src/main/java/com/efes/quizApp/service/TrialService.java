package com.efes.quizApp.service;

import com.efes.quizApp.dto.TrialDto;
import com.efes.quizApp.dto.WrapDto;
import com.efes.quizApp.entity.Quiz;
import org.springframework.data.util.Pair;


import java.util.List;

public interface TrialService {

    TrialDto save(TrialDto trialInfo);

    Boolean delete(TrialDto trialDto);

    TrialDto getById(Long devId, Long quizId, int noTrials);

    WrapDto<List<TrialDto>> getTrials(Long quizId, Long devId);

    WrapDto<List<Pair<Quiz, List<TrialDto>>>> getAllDevTrials(Long devId);

    WrapDto<List<TrialDto>> getQuizTrials(Long quizId);

}
