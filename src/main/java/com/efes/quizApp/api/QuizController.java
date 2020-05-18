package com.efes.quizApp.api;

import com.efes.quizApp.dto.QuizDto;
import com.efes.quizApp.service.impl.QuizServiceImpl;
import com.efes.quizApp.util.ApiPaths;
import com.efes.quizApp.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.QuizCtrl.CTRL)
@Api(value = ApiPaths.QuizCtrl.CTRL)
@Slf4j
public class QuizController {

    private final QuizServiceImpl quizServiceImpl;

    public QuizController(QuizServiceImpl quizServiceImpl){

        this.quizServiceImpl=quizServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get by pagination Operation",response = QuizDto.class)
    public ResponseEntity<TPage<QuizDto>> getAllByPagination(Pageable pageable){
        TPage<QuizDto> data = quizServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);

    }

    @GetMapping("/efe")
    @ApiOperation(value = "Get Operation",response = QuizDto.class)
    public ResponseEntity<QuizDto> getById(){
        QuizDto dto=  quizServiceImpl.getAllQuizzes();
        return ResponseEntity.ok(dto);

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Operation",response = QuizDto.class)
    public ResponseEntity<QuizDto> getByIdWithSql(@PathVariable(value = "id",required = true) Long id){
        QuizDto dto=  quizServiceImpl.getById(id);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    @ApiOperation(value = "create Operation",response = QuizDto.class)
    public ResponseEntity<QuizDto> createQuestion(@Valid @RequestBody QuizDto projectDto){
        return ResponseEntity.ok(quizServiceImpl.save(projectDto));

    }

    // @RequestMapping(path= "/update",method = RequestMethod.PUT)
    @PutMapping("/{id}")
//    @ApiOperation(value = "update Operation",response = ProjectDto.class)
    public ResponseEntity<QuizDto> updateQuestion(@PathVariable(value = "id",required = true) Long id, @Valid @RequestBody QuizDto projectDto){

        return ResponseEntity.ok( quizServiceImpl.update(id,projectDto));


    }

    @DeleteMapping("/{id}")
    //  @ApiOperation(value = "delete Operation",response = Boolean.class)
    public ResponseEntity <Boolean> delete(@PathVariable(value = "id",required = true) Long id){

        return ResponseEntity.ok(quizServiceImpl.delete(id));

    }


}
