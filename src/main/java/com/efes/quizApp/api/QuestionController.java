package com.efes.quizApp.api;

import com.efes.quizApp.dto.QuestionDto;
import com.efes.quizApp.service.impl.QuestionServiceImpl;
import com.efes.quizApp.util.ApiPaths;
import com.efes.quizApp.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
entity
repo
dto
service
service impl
controller

api path eklemek

 */


@RestController
@RequestMapping(ApiPaths.QuestionCtrl.CTRL)
//@Api(value = "Project APIs")
@Api(value = ApiPaths.QuestionCtrl.CTRL)
@Slf4j
public class QuestionController {


    private final QuestionServiceImpl questionServiceImpl;

    public QuestionController(QuestionServiceImpl questionServiceImpl){

        this.questionServiceImpl=questionServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get by pagination Operation",response = QuestionDto.class)
    public ResponseEntity<TPage<QuestionDto>> getAllByPagination(Pageable pageable){
        TPage<QuestionDto> data = questionServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);

    }

    @GetMapping("/efe")
    @ApiOperation(value = "Get Operation",response = QuestionDto.class)
    public ResponseEntity<QuestionDto> getById(){
        QuestionDto dto=  questionServiceImpl.getAllQuestions();
        return ResponseEntity.ok(dto);

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Operation",response = QuestionDto.class)
    public ResponseEntity<QuestionDto> getByIdWithSql(@PathVariable(value = "id",required = true) Long id){
        QuestionDto dto=  questionServiceImpl.getById(id);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    @ApiOperation(value = "create Operation",response = QuestionDto.class)
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionDto projectDto){
        return ResponseEntity.ok(questionServiceImpl.save(projectDto));

    }

    // @RequestMapping(path= "/update",method = RequestMethod.PUT)
    @PutMapping("/{id}")
//    @ApiOperation(value = "update Operation",response = ProjectDto.class)
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable(value = "id",required = true) Long id, @Valid @RequestBody QuestionDto projectDto){

        return ResponseEntity.ok( questionServiceImpl.update(id,projectDto));


    }

    @DeleteMapping("/{id}")
    //  @ApiOperation(value = "delete Operation",response = Boolean.class)
    public ResponseEntity <Boolean> delete(@PathVariable(value = "id",required = true) Long id){

        return ResponseEntity.ok(questionServiceImpl.delete(id));

    }





}
