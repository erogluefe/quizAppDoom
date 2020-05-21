package com.efes.quizApp.api;

import com.efes.quizApp.entity.Tag;
import com.efes.quizApp.service.impl.TagServiceImpl;
import com.efes.quizApp.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.TagCtrl.CTRL)
//@Api(value = "Project APIs")
@Api(value = ApiPaths.TagCtrl.CTRL)
@Slf4j
public class TagController {

    private final TagServiceImpl tagServiceImpl;


    public TagController(TagServiceImpl tagServiceImpl) {

        this.tagServiceImpl = tagServiceImpl;
    }


    @GetMapping("/getAll")
    @ApiOperation(value = "Get all questions",response = Tag[].class)
    public ResponseEntity<List<Tag>> getAllQuestions(){
        List<Tag> data = tagServiceImpl.getAllTags();
        return ResponseEntity.ok(data);

    }
}
