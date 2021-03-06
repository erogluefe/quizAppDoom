package com.efes.quizApp.api;

import com.efes.quizApp.dto.QuizDto;
import com.efes.quizApp.entity.costumGroupByClass;
import com.efes.quizApp.service.impl.ConsistOfServiceImpl;
import com.efes.quizApp.service.impl.QuestionServiceImpl;
import com.efes.quizApp.service.impl.QuizServiceImpl;
import com.efes.quizApp.service.impl.QuizTagsImpl;
import com.efes.quizApp.util.ApiPaths;
import com.efes.quizApp.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.QuizCtrl.CTRL)
@Api(value = ApiPaths.QuizCtrl.CTRL)
@Slf4j
public class QuizController {

    private final QuizServiceImpl quizServiceImpl;

    private final ConsistOfServiceImpl consistOfServiceImpl;

    private final QuestionServiceImpl questionServiceImpl;

    private final QuizTagsImpl quizTagsImpl;

    public QuizController(QuizServiceImpl quizServiceImpl, ConsistOfServiceImpl consistOfServiceImpl, QuestionServiceImpl questionServiceImpl, QuizTagsImpl quizTagsImpl) {
        this.quizServiceImpl = quizServiceImpl;
        this.consistOfServiceImpl = consistOfServiceImpl;
        this.questionServiceImpl = questionServiceImpl;
        this.quizTagsImpl = quizTagsImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get by pagination Operation",response = QuizDto.class)
    public ResponseEntity<TPage<QuizDto>> getAllByPagination(Pageable pageable){
        TPage<QuizDto> data = quizServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);

    }
    @GetMapping("/getAllQuiz")
    @ApiOperation(value = "Get All quizzes",response = QuizDto[].class)
    public ResponseEntity<List<QuizDto>> getAllQuizzesWithFind(){
        List<QuizDto> data = quizServiceImpl.getAllQuiz();
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
    @GetMapping("/hardest")
    @ApiOperation(value = "Get Hardest Quiz",response = QuizDto[].class)
    public ResponseEntity<List<QuizDto>> getHardestQuiz(){
        List<QuizDto> dtos= quizServiceImpl.getHardest();
        return ResponseEntity.ok(dtos);

    }
    @GetMapping("/easy")
    @ApiOperation(value = "Get Easitest Quiz",response = QuizDto[].class)
    public ResponseEntity<List<QuizDto>> getEasyQuiz(){
        List<QuizDto> dtos= quizServiceImpl.getEasy();
        return ResponseEntity.ok(dtos);

    }
    @GetMapping("/totalCount")
    @ApiOperation(value = "Get Number of Quiz",response = Integer.class)
    public ResponseEntity<Integer> getCountQuiz(){
        return ResponseEntity.ok(quizServiceImpl.getNumberOfQuiz());

    }
    @GetMapping("/time/{first}/{second}")
    @ApiOperation(value = "Get time of Quiz",response = QuizDto[].class)
    public ResponseEntity<List<QuizDto>> getEasyQuiz(@PathVariable(value = "first") int first,@PathVariable(value = "second") int second){
        List<QuizDto> dtos= quizServiceImpl.getSpecTime(first,second);
        return ResponseEntity.ok(dtos);

    }
    @GetMapping("/specQuiz/{name}")
    @ApiOperation(value = "Get time of Quiz",response = QuizDto[].class)
    public ResponseEntity<List<QuizDto>> getSpecNameQuiz(@PathVariable(value = "name") String name){
        List<QuizDto> dtos= quizServiceImpl.getSpecQuizzes(name);
        return ResponseEntity.ok(dtos);

    }
    @GetMapping("/admin/{adminId}")
    @ApiOperation(value = "Get time of Quiz",response = QuizDto[].class)
    public ResponseEntity<List<QuizDto>> getCreatorsQuiz(@PathVariable(value = "adminId") int adminId){
        List<QuizDto> dtos= quizServiceImpl.getSelectedCreatorQuiz(adminId);
        return ResponseEntity.ok(dtos);

    }





//    @GetMapping("/numberDif")
//    @ApiOperation(value = "Get Number of difficulty each Quiz",response = costumGroupByClass.class)
//    public ResponseEntity<List<costumGroupByClass>> getCountQuizDifficulty(){
//        return ResponseEntity.ok(quizServiceImpl.getNumberOfEachDifficulty());
//
//    }
//
//    @GetMapping("/{id}/{qid}")
//    @ApiOperation(value = "Get Operation",response = QuizDto.class)
//    public ResponseEntity<Boolean> addQuizQuestion(@PathVariable(value = "id") Long id,@PathVariable(value = "qid") Long qid){
//        return ResponseEntity.ok(quizServiceImpl.addQuestion(id,qid));
//
//    }

    @PostMapping
    @ApiOperation(value = "create Operation",response = QuizDto.class)
    public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody QuizDto quizDto){
        return ResponseEntity.ok(quizServiceImpl.save(quizDto));

    }
/*
    @PostMapping
    @ApiOperation(value = "create Operation with Questions",response = QuizDto.class)
    public ResponseEntity<QuizDto> createQuizWithQuestion(@Valid @RequestBody QuizDto quizDto,){
        return ResponseEntity.ok(quizServiceImpl.save(quizDto));

    }

 */

    // @RequestMapping(path= "/update",method = RequestMethod.PUT)
    @PutMapping("/{id}")
//    @ApiOperation(value = "update Operation",response = ProjectDto.class)
    public ResponseEntity<QuizDto> updateQuiz(@PathVariable(value = "id",required = true) Long id, @Valid @RequestBody QuizDto quizDto){

        consistOfServiceImpl.deleteAllQuizQuestions(id);

        quizTagsImpl.deleteAllTagsInQuiz(id);

      //  return ResponseEntity.ok( quizServiceImpl.update(id,quizDto));
        return ResponseEntity.ok(quizServiceImpl.update(id,quizDto));

    }

    @DeleteMapping("/{id}")
    //  @ApiOperation(value = "delete Operation",response = Boolean.class)
    public ResponseEntity <Boolean> delete(@PathVariable(value = "id",required = true) Long id){

        return ResponseEntity.ok(quizServiceImpl.delete(id));

    }


}
