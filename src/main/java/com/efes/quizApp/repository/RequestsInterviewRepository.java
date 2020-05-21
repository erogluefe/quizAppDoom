package com.efes.quizApp.repository;


import com.efes.quizApp.entity.RequestsInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestsInterviewRepository extends JpaRepository<RequestsInterview, Long> {
    List<RequestsInterview> getByDevId(Long devId);

    RequestsInterview getByDevIdAndCompanyId( Long devId, Long companyId);

    Boolean deleteByDevIdAndCompanyId(Long devId, Long companyId);
}
