package com.efes.quizApp.api;

import com.efes.quizApp.dto.UserDto;
import com.efes.quizApp.service.impl.UserServiceImpl;
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
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL)
@Slf4j
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl){

        this.userServiceImpl=userServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get by pagination Operation",response = UserDto.class)
    public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable){
        TPage<UserDto> data = userServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);

    }

    @GetMapping("/efe")
    @ApiOperation(value = "Get Operation",response = UserDto.class)
    public ResponseEntity<UserDto> getById(){
        UserDto dto=  userServiceImpl.getAllUsers();
        return ResponseEntity.ok(dto);

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Operation",response = UserDto.class)
    public ResponseEntity<UserDto> getByIdWithSql(@PathVariable(value = "id",required = true) Long id){
        UserDto dto=  userServiceImpl.getById(id);
        return ResponseEntity.ok(dto);

    }

    @GetMapping("{id}/{pass}")
    @ApiOperation(value = "login op",response = Boolean.class)
    public ResponseEntity<Boolean> loginToSystem(@PathVariable("id") Long id,@PathVariable("pass") String pass  ){
       return ResponseEntity.ok(userServiceImpl.loginTo(id,pass));
    }

    @PostMapping
    @ApiOperation(value = "create Operation",response = UserDto.class)
    public ResponseEntity<UserDto> createQuestion(@Valid @RequestBody UserDto projectDto){
        return ResponseEntity.ok(userServiceImpl.save(projectDto));

    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateQuestion(@PathVariable(value = "id",required = true) Long id, @Valid @RequestBody UserDto projectDto){

        return ResponseEntity.ok( userServiceImpl.update(id,projectDto));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Boolean> delete(@PathVariable(value = "id",required = true) Long id){

        return ResponseEntity.ok(userServiceImpl.delete(id));

    }

}
