package com.efes.quizApp.service.impl;


import com.efes.quizApp.entity.QuizTags;
import com.efes.quizApp.repository.QuizTagsRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizTagsImpl {

    private final QuizTagsRepository quizTagsRepository;


    public QuizTagsImpl(QuizTagsRepository quizTagsRepository) {
        this.quizTagsRepository = quizTagsRepository;
    }


    public List<QuizTags> getAllQuiz(Long id) {
        return quizTagsRepository.getByTagQuizId(id);
    }

}
