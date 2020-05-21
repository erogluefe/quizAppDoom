package com.efes.quizApp.repository;

import com.efes.quizApp.entity.QuizTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizTagsRepository extends JpaRepository<QuizTags,Long>  {


    List<QuizTags> getByTagQuizId(Long quizId);





}
