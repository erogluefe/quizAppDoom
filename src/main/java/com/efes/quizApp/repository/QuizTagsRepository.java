package com.efes.quizApp.repository;

import com.efes.quizApp.entity.QuizTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuizTagsRepository extends JpaRepository<QuizTags,Long>  {


    List<QuizTags> getByTagQuizId(Long quizId);


    @Transactional
    Integer deleteAllByTagQuizId(Long quizId);





}
