package com.efes.quizApp.api;


import com.efes.quizApp.dto.TrialDto;
import com.efes.quizApp.dto.WrapDto;
import com.efes.quizApp.entity.Quiz;
import com.efes.quizApp.service.impl.TrialServiceImpl;
import com.efes.quizApp.util.ApiPaths;
import org.springframework.data.util.Pair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.TrialCtrl.CTRL)
//@Api(value = "Project APIs")
@Api(value = ApiPaths.TrialCtrl.CTRL)
@Slf4j
public class TrialController {

    private final TrialServiceImpl trialServiceImpl;

    public TrialController(TrialServiceImpl trialServiceImpl){

        this.trialServiceImpl=trialServiceImpl;
    }

    @GetMapping("/{devId}")
    @ApiOperation(value = "Get operation based on devId", response = WrapDto.class)
    public ResponseEntity<WrapDto<List<Pair<Quiz,List<TrialDto>>>>> getByDevId(@PathVariable(value = "devId",required = true) Long devId){
        WrapDto<List<Pair<Quiz,List<TrialDto>>>> dto=  trialServiceImpl.getAllDevTrials(devId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/quiz/{quizId}")
    @ApiOperation(value = "Get operation based on quizId", response = WrapDto.class) // need to return a list
    public ResponseEntity<WrapDto<List<TrialDto>>> getByQuizId(@PathVariable(value = "quizId",required = true) Long quizId){
        WrapDto<List<TrialDto>> dto=  trialServiceImpl.getQuizTrials(quizId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{quizId}/{devId}")
    @ApiOperation(value = "Get all trials of a developer in a quiz", response = WrapDto.class)
    public ResponseEntity<WrapDto<List<TrialDto>>> getTrials(@PathVariable(value = "quizId") Long quizId
            ,@PathVariable(value = "devId") Long devId){
        WrapDto<List<TrialDto>> dto = trialServiceImpl.getTrials(quizId,devId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ApiOperation(value = "create trial", response = TrialDto.class)
    public ResponseEntity<TrialDto> createTrial(@Valid @RequestBody TrialDto postTrial){
        return ResponseEntity.ok(trialServiceImpl.save(postTrial));
    }
}
