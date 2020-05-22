package com.efes.quizApp.repository;

import com.efes.quizApp.entity.QuizTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuizTagsRepository extends JpaRepository<QuizTags,Long>  {



    @Transactional
    @Query(value = "SELECT * FROM quiz_tagss u WHERE u.id = :tagId", nativeQuery = true)
    QuizTags selectOneNativeSqlQuizTag(@Param("tagId") Long tagId);

    @Modifying
    @Query(value = "insert into quiz_tagss (tag_quiz_id,tag) VALUES (:QuizTagID,:tagOfQuiz)", nativeQuery = true)
    @Transactional
    void addByQuizTagNativeSql(@Param("QuizTagID") Long QuizTagID, @Param("tagOfQuiz") String tagOfQuiz);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM quiz_tagss  u WHERE u.tag_quiz_id = :idOfQuiz ",nativeQuery = true)
    void deleteByQuizTagNativeSql(@Param("idOfQuiz") Long idOfQuiz);


    List<QuizTags> getByTagQuizId(Long quizId);


    @Transactional
    Integer deleteAllByTagQuizId(Long quizId);





}
