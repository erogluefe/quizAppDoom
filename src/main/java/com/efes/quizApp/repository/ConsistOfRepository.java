package com.efes.quizApp.repository;


import com.efes.quizApp.entity.ConsistOf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsistOfRepository extends JpaRepository<ConsistOf, Long> {


  //  List<Integer> getByQuestionId(int questionId);

   // List<Integer> getByQuizId(int quizId);

   // ConsistOf getByQuizId(Long quizId);

    List<ConsistOf> getByQuizId(Long id);

    Page<ConsistOf> findAll(Pageable pageable);





   // List<Question> getByQuestionCodeContains(String description);


}
