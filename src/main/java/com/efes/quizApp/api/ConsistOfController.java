package com.efes.quizApp.api;

import com.efes.quizApp.dto.ConsDto;
import com.efes.quizApp.dto.QuestionDto;
import com.efes.quizApp.entity.ConsistOf;
import com.efes.quizApp.entity.Question;
import com.efes.quizApp.service.impl.ConsistOfServiceImpl;
import com.efes.quizApp.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.ConsCtrl.CTRL)
//@Api(value = "Project APIs")
@Api(value = ApiPaths.ConsCtrl.CTRL)
@Slf4j
public class ConsistOfController {

    private final ConsistOfServiceImpl consistOfServiceImpl;

    public ConsistOfController(ConsistOfServiceImpl consistOfServiceImpl) {
        this.consistOfServiceImpl = consistOfServiceImpl;
    }


    @PostMapping
    @ApiOperation(value = "create Operation",response = ConsistOf.class)
    public ResponseEntity<ConsDto> createConsistOf(@Valid @RequestBody ConsDto consi){
        return ResponseEntity.ok(consistOfServiceImpl.save(consi));

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all questions",response = QuestionDto.class)
    public ResponseEntity<List<QuestionDto>> getAllQuestions( @PathVariable(value = "id") Long id){
        List<QuestionDto> data = consistOfServiceImpl.getAlll(id);
        return ResponseEntity.ok(data);

    }




}
