package com.efes.quizApp.repository;

import com.efes.quizApp.entity.Trial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrialRepository extends JpaRepository<Trial, Long> {



    Trial getByDevIdAndQuizIdAndTrialNo(Long devId, Long quizId, int trialNo);

    Boolean deleteByDevIdAndQuizIdAndTrialNo(Long devId, Long quizId, int trialNo);

    List<Trial> getByQuizIdAndDevId(Long quizId, Long devId);

    List<Trial> getByDevId(Long devId);

    List<Trial> getByQuizIdOrderBySuccessRateAsc(Long quizId);
}
