package com.efes.quizApp.service;

import com.efes.quizApp.dto.ConsDto;
import com.efes.quizApp.entity.ConsistOf;
import com.efes.quizApp.entity.Question;
import com.efes.quizApp.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConsistOfService {

    ConsDto save(ConsDto consistOf);

    Boolean delete(ConsDto consDto);

    ConsDto update(Long id, ConsDto questionDto);

    ConsDto getAllQuestions();

    ConsDto getById(Long id);

    void deleteAllQuizQuestions(Long id);

    List<Question> getAllQuestionsInQuiz(Long quizId);



    ConsDto getByQuestionCode(String questionCode);

    List<ConsDto> getByQuestionCodeContains(String questionCode);


    TPage<ConsDto> getAllPageable(Pageable pageable);



}
