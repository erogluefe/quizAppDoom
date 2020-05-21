package com.efes.quizApp.service;

import com.efes.quizApp.dto.QuizDto;
import com.efes.quizApp.entity.Quiz;
import com.efes.quizApp.entity.costumGroupByClass;
import com.efes.quizApp.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {

    QuizDto save(QuizDto quizDto);

    QuizDto getAllQuizzes();

    QuizDto getById(Long id);

    QuizDto getByName(String name);

  //  Boolean addQuestion(Long Quesid,Long QuizId);

    List<QuizDto> getByNameContains(String name);

    TPage<QuizDto> getAllPageable(Pageable pageable);

    Boolean delete(QuizDto quizDto);

    QuizDto update(Long id, QuizDto quizDto);


    List<QuizDto> getHardest();

    List<QuizDto> getEasy();

    int getNumberOfQuiz();

   // List<costumGroupByClass> getNumberOfEachDifficulty();

    List<QuizDto> getSpecTime(int first,int second);

    List<QuizDto> getSpecQuizzes(String name);

    List<QuizDto> getSelectedCreatorQuiz(int creatorName);

    List<QuizDto> getAllQuiz();




}
