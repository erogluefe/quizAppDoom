package com.efes.quizApp.repository;


import com.efes.quizApp.entity.Quiz;
import com.efes.quizApp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface QuizRepository extends JpaRepository<Quiz,Long> {



    Quiz getById(Long id);

    Quiz getByName(String name);


    List<Quiz> getByNameContains(String description);

    Quiz getByNameAndIdNot(String name, Long id);

    Page<Quiz> findAll(Pageable pageable);

    List<Quiz> findAll(Sort sort);



    @Query(
            value = "SELECT * FROM quiz u WHERE u.id = 23",
            nativeQuery = true)
    Quiz findSpecificquiz();

    // buraya istediğimiz rakamı eklemeye bakalım bi ara.

}





