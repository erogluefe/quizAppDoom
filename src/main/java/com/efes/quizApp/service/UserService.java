package com.efes.quizApp.service;


import com.efes.quizApp.dto.UserDto;
import com.efes.quizApp.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {


    UserDto save(UserDto userDto);

    UserDto getAllUsers();

    UserDto getById(Long id);

    UserDto getByUsername(String username);

    List<UserDto> getByUsernameContains(String username);

    TPage<UserDto> getAllPageable(Pageable pageable);

    Boolean delete(UserDto userDto);

    UserDto update(Long id, UserDto userDto);
}
