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


    List<ConsistOf> getByQuizId(Long id);

    Page<ConsistOf> findAll(Pageable pageable);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM consist_of u WHERE u.question_id = :idOfQuestion ",nativeQuery = true)
    void deleteSpecificQuestions(@Param("idOfQuestion") Long idOfQuestion);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM consist_of u WHERE u.question_id = :idOfQuestion AND u.quiz_id = :idOfQuiz ",nativeQuery = true)
    void deleteSpecificQuestionsInSpecificQuiz(@Param("idOfQuestion") Long idOfQuestion,@Param("idOfQuiz") Long idOfQuiz);



    /*
    @Modifying
    @Query("delete from Book b where b.title=:title")
    void deleteBooks(@Param("title") String title);


     */






}
