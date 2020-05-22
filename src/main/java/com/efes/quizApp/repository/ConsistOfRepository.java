package com.efes.quizApp.repository;


import com.efes.quizApp.entity.ConsistOf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ConsistOfRepository extends JpaRepository<ConsistOf, Long> {



    @Transactional
    @Query(value = "SELECT * FROM consist_of u WHERE u.id = :consId", nativeQuery = true)
    ConsistOf selectOneNativeSql(@Param("consId") Long consId);

    @Modifying
    @Query(value = "insert into consist_of (question_id,quiz_id) VALUES (:nameOfQuiz,:idOfQuiz)", nativeQuery = true)
    @Transactional
    void addByConsNativeSql(@Param("nameOfQuiz") String nameOfQuiz, @Param("idOfQuiz") Long idOfQuiz);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM consist_of  u WHERE u.quiz_id = :idOfQuiz ",nativeQuery = true)
    void deleteByConsNativeSql(@Param("idOfQuiz") Long idOfQuiz);



    @Transactional
    @Modifying
    @Query(value = "DELETE FROM consist_of u WHERE u.question_id = :idOfQuestion ",nativeQuery = true)
    void deleteSpecificQuestions(@Param("idOfQuestion") Long idOfQuestion);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM consist_of u WHERE u.quiz_id = :quizIdd ",nativeQuery = true)
    void deleteAllQuestionsInQuiz(@Param("quizIdd") Long quizIdd);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM consist_of u WHERE u.question_id = :idOfQuestion AND u.quiz_id = :idOfQuiz ",nativeQuery = true)
    void deleteSpecificQuestionsInSpecificQuiz(@Param("idOfQuestion") Long idOfQuestion,@Param("idOfQuiz") Long idOfQuiz);



    List<ConsistOf> getByQuizId(Long id);

    Page<ConsistOf> findAll(Pageable pageable);

    /*
    @Modifying
    @Query("delete from Book b where b.title=:title")
    void deleteBooks(@Param("title") String title);


     */






}
