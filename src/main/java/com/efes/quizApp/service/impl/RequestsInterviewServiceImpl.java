package com.efes.quizApp.service.impl;

import com.efes.quizApp.dto.RequestsInterviewDto;
import com.efes.quizApp.dto.WrapDto;
import com.efes.quizApp.entity.RequestsInterview;
import com.efes.quizApp.entity.Status;
import com.efes.quizApp.repository.RequestsInterviewRepository;
import com.efes.quizApp.service.RequestsInterviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RequestsInterviewServiceImpl implements RequestsInterviewService {

    private ModelMapper modelMapper;
    private RequestsInterviewRepository reqRepo;

    public RequestsInterviewServiceImpl(ModelMapper modelMapper, RequestsInterviewRepository reqRepo){
        this.modelMapper = modelMapper;
        this.reqRepo = reqRepo;
    }

    @Override
    public RequestsInterviewDto save(RequestsInterviewDto req_int_info) {
//        RequestsInterview requestsInterview = reqRepo.getByDevIdAndCompanyId(req_int_info.getDevId(), req_int_info.getCompanyId());
//
//        if(requestsInterview != null)
//            throw new IllegalArgumentException("Such Interview Request exists");

        RequestsInterview requestsInterview = new RequestsInterview(req_int_info.getDevId(), req_int_info.getCompanyId(),
                Status.PENDING, req_int_info.getDuration(), new Date());

        reqRepo.save(requestsInterview);
        return modelMapper.map(requestsInterview, RequestsInterviewDto.class);
//        return  new RequestsInterviewDto(requestsInterview.getDevId(), requestsInterview.getCompanyId(),
//                "PENDING", requestsInterview.getDuration(), requestsInterview.getStartDate());

    }

    @Override
    @Transactional
    public void delete(RequestsInterviewDto req_int_info) {
        reqRepo.delete(modelMapper.map(req_int_info, RequestsInterview.class));
    }

    @Override
    @Transactional
    public RequestsInterviewDto updateStatus(RequestsInterviewDto req_int_info) {
        RequestsInterview reqDb = reqRepo.getByDevIdAndCompanyId(req_int_info.getDevId(),req_int_info.getCompanyId());
        if(reqDb == null)
            throw new IllegalArgumentException("Such interview does not exist");
        reqDb.setStatus(req_int_info.getStatus());

        reqRepo.save(reqDb);
        return modelMapper.map(reqDb, RequestsInterviewDto.class);
    }

    @Override
    public WrapDto<List<RequestsInterviewDto>> getDevInterviews(Long devId) {
        List<RequestsInterview> interviews = reqRepo.getByDevId(devId);
        List<RequestsInterviewDto> interviewDto = new ArrayList<RequestsInterviewDto>();

        for (RequestsInterview interview: interviews) {
            interviewDto.add(modelMapper.map(interview, RequestsInterviewDto.class));
        }

        return new WrapDto<List<RequestsInterviewDto>>(interviewDto);
    }
}
