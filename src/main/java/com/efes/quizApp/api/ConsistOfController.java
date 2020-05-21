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
    @ApiOperation(value = "Get all questions",response = QuestionDto[].class)
    public ResponseEntity<List<QuestionDto>> getAllQuestions( @PathVariable(value = "id") Long id){
        List<QuestionDto> data = consistOfServiceImpl.getAlll(id);
        return ResponseEntity.ok(data);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete Operation",response = Boolean.class)
    public ResponseEntity <Boolean> delete(@PathVariable(value = "id") Long id){

        return ResponseEntity.ok(consistOfServiceImpl.delete(id));

    }

    @DeleteMapping("/spec/{id}")
    @ApiOperation(value = "delete Operation with all selected question",response = Boolean.class)
    public void deleteWithSpec(@PathVariable(value = "id") Long id){
        consistOfServiceImpl.deleteWithSqlQuer(id);
        //return ResponseEntity.ok();

    }

    @GetMapping("/getSpec/{id}")
    @ApiOperation(value = "Get Operation with spec consist of",response = ConsDto.class)
    public ResponseEntity<ConsDto> getById(@PathVariable(value = "id") Long id){
        ConsDto dto=  consistOfServiceImpl.getById(id);
        return ResponseEntity.ok(dto);

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation",response = ConsDto.class)
    public ResponseEntity<ConsDto> update(@PathVariable(value = "id")Long id,@Valid @RequestBody ConsDto consDto){

        return ResponseEntity.ok( consistOfServiceImpl.update(id,consDto));

    }




}
