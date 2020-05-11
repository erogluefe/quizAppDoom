package com.efes.quizApp.service;

import com.efes.quizApp.dto.QuestionDto;
import com.efes.quizApp.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {

    QuestionDto save(QuestionDto project);

    QuestionDto getAllQuestions();

    QuestionDto getById(Long id);

    QuestionDto getByQuestionCode(String questionCode);

    List<QuestionDto> getByQuestionCodeContains(String questionCode);

    TPage<QuestionDto> getAllPageable(Pageable pageable);

    Boolean delete(QuestionDto project);


    QuestionDto update(Long id, QuestionDto questionDto);
}
