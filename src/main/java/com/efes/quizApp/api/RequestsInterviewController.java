package com.efes.quizApp.api;

import com.efes.quizApp.dto.RequestsInterviewDto;
import com.efes.quizApp.dto.WrapDto;
import com.efes.quizApp.entity.RequestsInterview;

import com.efes.quizApp.service.impl.RequestsInterviewServiceImpl;
import com.efes.quizApp.util.ApiPaths;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.RequestsInterviewCtrl.CTRL)
//@Api(value = "Project APIs")
@Api(value = ApiPaths.RequestsInterviewCtrl.CTRL)
@Slf4j
public class RequestsInterviewController {

    private final RequestsInterviewServiceImpl requestsInterviewServiceImpl;

    public RequestsInterviewController(RequestsInterviewServiceImpl requestsInterviewServiceImpl) {
        this.requestsInterviewServiceImpl = requestsInterviewServiceImpl;
    }

    @GetMapping("/{devId}")
    @ApiOperation(value = "get all requests of a developer", response = WrapDto.class)
    public ResponseEntity<WrapDto<List<RequestsInterviewDto>>> getByDevId(
            @PathVariable(value = "devId", required = true) Long devId){
        WrapDto<List<RequestsInterviewDto>> reqList = requestsInterviewServiceImpl.getDevInterviews(devId);
        return ResponseEntity.ok(reqList);
    }

    @PutMapping
    @ApiOperation(value = "updates status of a interview request", response = RequestsInterviewDto.class)
    public ResponseEntity<RequestsInterviewDto> updateStatus(@Valid @RequestBody RequestsInterviewDto req_info){
        RequestsInterviewDto toWrap = requestsInterviewServiceImpl.updateStatus(req_info);
        return ResponseEntity.ok(toWrap);
    }

    @PostMapping
    @ApiOperation(value = "create a interview request", response = RequestsInterview.class)
    public ResponseEntity<RequestsInterviewDto> createInterviewRequest(
            @Valid @RequestBody RequestsInterviewDto req_info){
        RequestsInterviewDto savedReq = requestsInterviewServiceImpl.save(req_info);
        return ResponseEntity.ok(savedReq);
    }

    @DeleteMapping
    @ApiOperation(value = "delete an interview request", response = Boolean.class)
    public ResponseEntity<Boolean> deleteInterviewRequest(@Valid @RequestBody RequestsInterviewDto req_info){
        Boolean isDeleted = requestsInterviewServiceImpl.delete(req_info);
        return ResponseEntity.ok(isDeleted);
    }
}
