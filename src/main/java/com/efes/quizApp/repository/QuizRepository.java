package com.efes.quizApp.repository;


import com.efes.quizApp.entity.Quiz;
import com.efes.quizApp.entity.User;
import com.efes.quizApp.entity.costumGroupByClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.List;

@Repository

public interface QuizRepository extends JpaRepository<Quiz,Long> {

    @Modifying
    @Query(value = "insert into quiz (quiz_name,quiz_id) VALUES (:nameOfQuiz,:idOfQuiz)", nativeQuery = true)
    @Transactional
    void addByNativeSql(@Param("nameOfQuiz") String nameOfQuiz, @Param("idOfQuiz") Long idOfQuiz);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM quiz u WHERE u.quiz_name = :nameOfQuiz ",nativeQuery = true)
    void deleteByNativeSql(@Param("nameOfQuiz") String nameOfQuiz);

    @Modifying
    @Query(value = "update quiz u set u.quiz_name = :nameOfQuiz, u.quiz_id = :IdOfQuiz where u.difficulty = :dif",nativeQuery = true)
    @Transactional
    void updateQuizInfoByNativeSql(@Param( "nameOfQuiz")String nameOfQuiz, @Param( "IdOfQuiz")Long IdOfQuiz,@Param( "dif") Integer dif);



    @Query(
            value = "SELECT * FROM quiz u WHERE u.id = 23",
            nativeQuery = true)
    Quiz findSpecificquiz();


    @Transactional
    @Query(
            value = "SELECT * FROM quiz u WHERE difficulty=(SELECT MAX(difficulty) FROM quiz)",
            nativeQuery = true)
    List<Quiz> findTheMostDifficultQuiz();


    @Transactional
    @Query(
            value = "SELECT * FROM quiz u WHERE difficulty=(SELECT MIN(difficulty) FROM quiz)",
            nativeQuery = true)
    List<Quiz> findTheMostEasyQuiz();

    @Transactional
    @Query(
            value = "SELECT count(*) from quiz",
            nativeQuery = true)
    int findQuizNumber();


    @Transactional
    @Query(
            value = "   SELECT COUNT(id) as countryFor , difficulty as countFor FROM quiz GROUP BY difficulty",
            nativeQuery = true)
    List<Tuple> findNumberOfEachDifficulty();



    @Transactional
    @Query(
            value = "SELECT * FROM quiz WHERE time_limit BETWEEN :firstt AND :secondd",
            nativeQuery = true)
    List<Quiz> findSpecificTime(@Param("firstt") int first, @Param("secondd") int second);

    @Transactional
    @Query(
            value = "SELECT * FROM quiz u WHERE LOWER (u.name) LIKE LOWER (%:quizName% )",
            nativeQuery = true)
    List<Quiz> findSpecificNameQuizzes(@Param("quizName") String quizName);





    Quiz getById(Long id);

    Quiz getByName(String name);


    List<Quiz> getByNameContains(String description);

    Quiz getByNameAndIdNot(String name, Long id);

    Page<Quiz> findAll(Pageable pageable);

    List<Quiz> findAll(Sort sort);


    List<Quiz> getByCuratedId(int quizCreator);

    // buraya istediğimiz rakamı eklemeye bakalım bi ara.

}







