package com.efes.quizApp.service;

import com.efes.quizApp.dto.QuizDto;
import com.efes.quizApp.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {

    QuizDto save(QuizDto quizDto);

    QuizDto getAllQuizzes();

    QuizDto getById(Long id);

    QuizDto getByName(String name);

    List<QuizDto> getByNameContains(String name);

    TPage<QuizDto> getAllPageable(Pageable pageable);

    Boolean delete(QuizDto quizDto);

    QuizDto update(Long id, QuizDto quizDto);
}
