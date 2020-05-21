package com.efes.quizApp.service;

import com.efes.quizApp.dto.RequestsInterviewDto;
import com.efes.quizApp.dto.WrapDto;

import java.util.List;

public interface RequestsInterviewService {
    RequestsInterviewDto save(RequestsInterviewDto req_int_info);

    Boolean delete(RequestsInterviewDto req_int_info);

    RequestsInterviewDto updateStatus(RequestsInterviewDto req_int_info);

    WrapDto<List<RequestsInterviewDto>> getDevInterviews(Long devId);
}
